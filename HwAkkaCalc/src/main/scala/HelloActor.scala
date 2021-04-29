import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class Operatio  {

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

private class HelloActor extends Operatio with Actor {
  def receive = {
    case i:Int => println(i)
    case s:String => toInteg(s)
  }
  def toInt(s: Char): Option[Int] = {
    if(s.toInt>47 & 58>s.toInt) {
      Some(s.toInt-48)
    } else None
  }
  def toInteg(a:String): Int = {
    var num = ""
    var intlist = List[Int]()
    var operlist = List[Int]()
    for (b <- a) {
      if (toInt(b) == None) {
        b match {
          case '+' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ 1
            num = ""
          }
          case '-' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ 2
            num = ""
          }
          case '/' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ 3
            num = ""
          }
          case '*' => {
            intlist = intlist :+ num.toInt
            operlist = operlist :+ 4
            num = ""
          }
          case '=' => {
            intlist = intlist :+ num.toInt

            num = ""
          }
          case _ => None
        }
      } else num += b
    }
    var digit=intlist(0)
    for(i <- 0 to (intlist.length-2)){
      operlist(i) match {
        case 1 => digit=sum(digit,intlist(i+1))
        case 2 => digit=min(digit,intlist(i+1))
        case 4 => digit=mul(digit,intlist(i+1))
        case 3 => digit=div(digit,intlist(i+1))
      }
    }
    println(digit)
    digit
  }
}

object Main {
  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "1+1+3+5="

}