import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import scala.io.StdIn.{readLine}

object Main extends App {

  var numb="";

  def start(): Unit = {

    numb="1+2+3+4="
    akk(numb)
  }
  def akk(n:String): Unit ={
    val system: ActorSystem[HelloWorldMain.SayHello] =
      ActorSystem(HelloWorldMain(), "hello")

    system ! HelloWorldMain.SayHello(n)
  }
  start()

}
