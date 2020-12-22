package ru.bmstu.hadoop.lab4;

public class ResultMessage {
    private final String packageId;

    public ResultMessage(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
