/*
import scala.io.StdIn.{readLine}
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
case class Calculate(value1: Double, numbe: String, operation: String)

 class Cal extends Actor {

  override def receive:  Receive = {
    case c: Calculate =>

      val response = c.operation match {
        case "+" => {
          c.value1 = c.value1
        }
        case "-" => {
          c.value1 - c.value1
        }
        case "/" => {
          c.value1 / c.value1
        }
        case "*" => {
          c.value1 * c.value1
        }
        case _ => -1
      }
      println(response)
      Arra ! response
  }

}
class Arra extends Actor {
  override def receive:  Receive = {
    case c: Calculate =>
      val senderRef = sender()
      val response = c.operation match {
        case "+" => {
          c.value1 + c.value2
        }
        case "-" => {
          c.value1 - c.value2
        }
        case "/" => {
          c.value1 / c.value2
        }
        case "*" => {
          c.value1 * c.value2
        }
        case _ => -1
      }
      println(response)
      senderRef ! response
  }
  def toInt(s: Char): Option[Int] = {
    if(s.toInt>47 & 58>s.toInt) {
      Some(s.toInt-48)
    } else None
  }
  def toInteg(a:String): Int = {
    var num = ""
    var intlist = List[Int]()
    var operlist = List[Char]()
    for (b <- a) {
      if (toInt(b) == None) {
        b match {
          case '+' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ "+"
            num = ""
          }
          case '-' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ "-"
            num = ""
          }
          case '/' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ "/"
            num = ""
          }
          case '*' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ "*"
            num = ""
          }
          case '=' => {
            intlist = intlist :+ num.toInt
            operlist = intlist :+ "="
            num = ""
          }
          case _ => None
        }
      } else num += b
    }
  }

}
object Maines extends App {
  val system = ActorSystem("MySystemTwo")
  val Cal = system.actorOf(Props[Cal], name = "Cal")
  Cal ! Calculate(0,"1+2-1+4=","+")

}
*/

