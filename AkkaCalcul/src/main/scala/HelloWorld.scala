import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

object HelloWorld {
  case class Calculate(value1: Double, numbe: String, operation: String)
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: List[Int], from: ActorRef[Greet])
  final case class Operat(whom: List[Int], fromTo: ActorRef[Greeted])
  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    var num = ""
    var intlist=List[Int]()
    var operlist=List[Int]()
    for (b <- message.whom){
      if(toInt(b)==None){
        b match {
          case '+' => {
            intlist =intlist:+ num.toInt
            operlist= operlist:+ 1
            num=""
          }
          case '-' => {
            intlist =intlist:+ num.toInt
            operlist=operlist:+ 2
            num=""
          }
          case '/' => {
            intlist =intlist:+ num.toInt
            operlist=operlist:+ 3
            num=""
          }
          case '*' => {
            intlist =intlist:+ num.toInt
            operlist=operlist:+ 4
            num=""
          }
          case '=' => {
            intlist =intlist:+ num.toInt

            num=""
          }
          case _ => None
        }
      }else num+=b
    }
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(intlist, context.self)
    Behaviors.same
  }
  def toInt(s: Char): Option[Int] = {
    if(s.toInt>47 & 58>s.toInt) {
      Some(s.toInt-48)
    } else None
  }
}
