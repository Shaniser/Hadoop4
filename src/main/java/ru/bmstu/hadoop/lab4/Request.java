package ru.bmstu.hadoop.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("packageId")
    private final String packageId;
    @JsonProperty("jsScript")
    private final String jsScript;
    @JsonProperty("functionName")
    private final String functionName;
    @JsonProperty("tests")
    private final String tests;

    public Request(String packageId, String jsScript, String functionName, String tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }
}
