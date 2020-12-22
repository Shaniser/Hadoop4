package ru.bmstu.hadoop.lab4;

import java.util.ArrayList;

public class ResultMessage {
    private final String packageId;
    private final ArrayList<String> result;

    public ResultMessage(String packageId, ArrayList<String> result) {
        this.packageId = packageId;
        this.result = result;
    }

    public String getPackageId() {
        return packageId;
    }

    public ArrayList<String> getResult() {
        return result;
    }
}
