package ru.bmstu.hadoop.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class Tester {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//        engine.eval("var f = function(a, b) {return a + b}");
//        Invocable invocable = (Invocable) engine;
//        System.out.println(invocable.invokeFunction("f", 5, 12).toString());

        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        ActorRef router = system.actorOf(Props.create(RouteActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Tester instance = new Tester();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute(router).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer);
                System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute(ActorRef router) {
        return route(
                get(
                        () -> parameter(
                                "packageId",
                                (id) -> {
                                    Future<Object> future = Patterns.ask(router, new ResultMessage(id), 5);
                                    return completeOKWithFuture(future, Jackson.marshaller());
                                }
                        )
                ),
                post(
                        () -> entity(Jackson.unmarshaller(Request.class),
                                (requestBody) -> {
                                    router.tell(requestBody, ActorRef.noSender());
                                    return complete("Test Accepted \n");
                                })
                )
        );
    }
}
