package ru.bmstu.hadoop.lab4;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;


public class RouteActor extends AbstractActor {
    private final ActorRef actorExecutor = getContext().actorOf(
            new RoundRobinPool(5).props(Props.create(ExecuteActor.class)));
    private final ActorRef 

    @Override
    public Receive createReceive() {
        return null;
    }
}
