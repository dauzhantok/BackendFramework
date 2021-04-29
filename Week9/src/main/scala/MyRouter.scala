
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}

import scala.concurrent.ExecutionContext

trait  Router extends Calculation{
  def route:Route
}

class MyRouter(todoRepository: TodoRepository)(implicit system: ActorSystem[_],  ex:ExecutionContext) extends Router with  Directives{
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  override def route = concat(
    path("solv"/Segment) { str =>
      get {
        val system: ActorSystem[HelloWorldMain.SayHello] = {
          ActorSystem(HelloWorldMain(), "hello")
        }
        system ! HelloWorldMain.SayHello("ss",Calculate(0,str,'+'))
        complete()
      }
    }
  )
}