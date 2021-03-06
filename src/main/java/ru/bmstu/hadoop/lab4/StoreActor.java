package ru.bmstu.hadoop.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreActor extends AbstractActor {
    private final HashMap<String, ArrayList<String>> testResults = new HashMap<>();


    @Override
    public Receive createReceive() {
        ArrayList<String> results = new ArrayList<>();
        results.add("test");
        return ReceiveBuilder.create()
                .match(ResultMessage.class, msg -> {
                    testResults.put("2", results);
                    String id = msg.getPackageId();
                    ArrayList<String> result = testResults.get(id);
                    sender().tell(new ResultMessage(id, result), sender());
                }).build();
    }
}
