
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

object Opera {
  def apply(): Behavior[HelloWorld.Operat] = Behaviors.receive { (context, message) =>
    context.log.info("Operat {}!", message.whom)
    message.fromTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }
}
