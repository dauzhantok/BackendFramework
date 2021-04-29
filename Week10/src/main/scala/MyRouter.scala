import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import TodoRepository.TodoNotFound
import scala.concurrent.ExecutionContext

trait  Router {
  def route:Route

}

class MyRouter(todoRepository: TodoRepository)(implicit system: ActorSystem[_],  ex:ExecutionContext)
  extends Router
    with  Directives
    with TodoDirectives
    with ValidatorDirectives {

  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  override def route = concat(
    path("ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "pong"))
      }
    },
    path("todos") {
      pathEndOrSingleSlash {
        concat(
          get {
            handleWithGeneric(todoRepository.all()) {
              todos => complete(todos)
            }
          },
          post {
            entity(as[CreateTodo]) { createTodo =>
              validateWith(CreateTodoValidator)(createTodo){
                handle(todoRepository.create(createTodo.title, createTodo)){
                  case TodoRepository.TodoTitleFound(_) =>
                    ApiError.todoTitleFound(createTodo.title)
                  case _ =>
                    ApiError.generic
                }{
                  todo => complete(todo)
                }
              }
            }
          },
            put {
              entity(as[UpdateTodo]) { updateTodo =>
                validateWith(UpdateTodoValidator)(updateTodo) {

                  handle(todoRepository.update(updateTodo.id, updateTodo)) {
                    case TodoRepository.TodoNotFound(_) =>
                      ApiError.todoNotFound(updateTodo.id)
                    case _ =>
                      ApiError.generic
                  } { todo =>
                    complete(todo)
                  }
                }
              }
            }

        )
      }
    }
  )
}
//          put {
//            entity(as[UpdateTodo]) { updateTodo =>
//
//              validateWith(UpdateTodoValidator)(updateTodo){
//                handleWithGeneric(todoRepository.update(updateTodo)){todo => complete(todo)}
//              }
//            }
//          }
//todo add update route