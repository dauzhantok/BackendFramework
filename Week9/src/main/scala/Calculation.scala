import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.complete

import scala.util.control.Breaks._
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.{Directives, Route}


case class Calculate(value1: Int, numbe: String, operation: Char)


object OddOperation extends Router with  Directives{
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._
  final case class Greet(whom: Calculate, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: Calculate, from: ActorRef[Greet])
  private var s:String="s"
  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    val a = toInteg( message.whom)

    if (a.operation == '=') {
      s=a.value1.toString
      println(s)
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s))
      println(s+"r")
      Behaviors.stopped
    } else{
      message.replyTo ! Greeted(a, context.self)
      Behaviors.same
    }
  }
  override def route = concat(
      get {
        println(s+"route")
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s))
      }
  )
}

object EvenOperation extends Router with  Directives{
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._
  private var s:String="s"
  def apply(): Behavior[OddOperation.Greeted] =
    Behaviors.receive { (context, message) =>
      val a = toInteg( message.whom)
      if (a.operation == '=') {
        s=a.value1.toString
        println(s)
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s))
        println(s+"r")
        Behaviors.stopped
      } else {
        message.from ! OddOperation.Greet(a, context.self)
        Behaviors.same
      }
    }
  override def route = concat(
      get {
        println(s+"route")
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s))
      }
  )
}
class Calculation{
  def toInt(s: Char): Option[Int] = {
    if (s.toInt > 47 & 58 > s.toInt) {
      Some(s.toInt - 48)
    } else None
  }

  def toInteg(a: Calculate): Calculate = {
    var num = ""
    var index = 0
    var digit = a.value1
    var ch = 's'
    var opera = a.operation
    breakable {
      for (b <- a.numbe) {
        index += 1
        if (toInt(b) == None) {
          b match {
            case '+' => ch = '+'
            case '-' => ch = '-'
            case '/' => ch = '/'
            case '*' => ch = '*'
            case '=' => ch = '='
          }
          break
        } else num += b
      }
    }

    opera match {
      case '+' => digit=digit+num.toInt
      case '-' => digit=digit-num.toInt
      case '*' => digit=digit*num.toInt
      case '/' => digit=digit/num.toInt
    }
    val numb = a.numbe.substring(index)
    Calculate(digit, numb, ch)
  }
}

object HelloWorldMain extends Calculation {

  final case class SayHello(name: String, cal: Calculate)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(OddOperation(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(EvenOperation(), message.name)
        greeter ! OddOperation.Greet(message.cal, replyTo)
        Behaviors.same
      }
    }

}


