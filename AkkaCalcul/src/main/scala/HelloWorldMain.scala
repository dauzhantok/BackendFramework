import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

object HelloWorldMain extends Operation{
  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(HelloWorld(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(HelloWorldBot(), message.name)
        greeter ! HelloWorld.Greet(message.name, replyTo)
        Behaviors.same
      }
    }












  def toInt(s: Char): Option[Int] = {
    if(s.toInt>47 & 58>s.toInt) {
      Some(s.toInt-48)
    } else None
  }
  def toInteg(a:String): Int ={
    var num = ""
    var intlist=List[Int]()
    var operlist=List[Int]()
    for (b <- a){
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

    var digit=intlist(0)
    for(i <- 0 to (intlist.length-2)){
      operlist(i) match {
        case '+' => digit=sum(digit,intlist(i+1))
        case '-' => digit=min(digit,intlist(i+1))
        case '*' => digit=mul(digit,intlist(i+1))
        case '/' => digit=div(digit,intlist(i+1))
      }
    }
    digit
  }

}
class Operation {
  def sum(a: Int,b: Int): Int ={
    val sum=a+b
    sum
  }
  def min(a: Int,b: Int): Int ={
    val min=a-b
    min
  }
  def div(a: Int,b: Int): Int ={
    val div=a/b
    div
  }
  def mul(a: Int,b: Int): Int ={
    val mul=a*b
    mul
  }
}

