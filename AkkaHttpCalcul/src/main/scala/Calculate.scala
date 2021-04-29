import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

import scala.util.control.Breaks._

case class Calculate(value1: Int, numbe: String, operation: Char)


object OddOperation extends Calculation {
  final case class Greet(whom: Calculate, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: Calculate, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    val a = toInteg( message.whom)
    if (a.operation == '=') {
      println("OddOperation = "+a.value1)
      Behaviors.stopped
    } else{
      message.replyTo ! Greeted(a, context.self)
      Behaviors.same
    }
  }
}

object EvenOperation extends Calculation{
  def apply(): Behavior[OddOperation.Greeted] =
    Behaviors.receive { (context, message) =>
      val a = toInteg( message.whom)

      if (a.operation == '=') {
        println("EvenOperation = "+a.value1)
        Behaviors.stopped
      } else {
        message.from ! OddOperation.Greet(a, context.self)
        Behaviors.same
      }
    }
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

  def day: Unit = {
    var numb=""
    val system: ActorSystem[HelloWorldMain.SayHello] = {
      ActorSystem(HelloWorldMain(), "hello")
    }
    system ! HelloWorldMain.SayHello("ss",Calculate(0,"1+1+3+5=",'+'))

  }
}



