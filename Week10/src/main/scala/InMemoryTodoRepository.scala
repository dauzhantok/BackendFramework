import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import TodoRepository.{TodoNotFound, TodoTitleFound}
class InMemoryTodoRepository(initialTodos:Seq[Todo] = Seq.empty)(implicit ec:ExecutionContext) extends TodoRepository {

  private var todos: Vector[Todo] = initialTodos.toVector

  override def all(): Future[Seq[Todo]] = Future.successful(todos)

  override def done(): Future[Seq[Todo]] = Future.successful(todos.filter(_.done))

  override def pending(): Future[Seq[Todo]] = Future.successful(todos.filterNot(_.done))

  override def create(title: String, createTodo: CreateTodo): Future[Todo] = {
    todos.find(_.title == title) match {
      case Some(foundTodo) =>
        Future.failed(TodoTitleFound(title))
      case None =>
        val todo = Todo(
          id = UUID.randomUUID().toString,
          title = createTodo.title,
          description = createTodo.description,
          done = false
        )
        todos = todos :+ todo
        Future.successful(todo)
    }
    }

  override def update(id: String, updateTodo: UpdateTodo): Future[Todo] = {
    todos.find(_.id == id) match {
      case Some(foundTodo) =>
        val newTodo = updateHelper(foundTodo, updateTodo)
        todos = todos.map(t => if (t.id == id) newTodo else t)
        Future.successful(newTodo)
      case None =>
        Future.failed(TodoNotFound(id))
    }
  }
  private def updateHelper(todo: Todo, updateTodo: UpdateTodo): Todo = {
    val t1 = updateTodo.title.map(title => todo.copy(title = title)).getOrElse(todo)
    val t2 = updateTodo.description.map(description => t1.copy(description = description)).getOrElse(t1)
    updateTodo.done.map(done => t2.copy(done = done)).getOrElse(t2)
  }
}
//  override def update(updateTodo: UpdateTodo): Future[Todo] = {
//    Future.successful {
//
//      val todo = Todo(
//        id = updateTodo.toString,
//        title = updateTodo.title,
//        description = updateTodo.description,
//        done = updateTodo.done
//      )
//      for (t <- todos) {
//        if(t.id==updateTodo.id)
//      }
//      todos = todos :+ todo
//      todo
//    }
//  }