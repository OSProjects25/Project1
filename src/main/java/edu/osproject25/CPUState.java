package edu.osproject25;

public class CPUState {
    private String pName;
    private int startTime;
    private int completionTime;

    public CPUState(String pName, int startTime, int completionTime) {
        this.pName = pName;
        this.startTime = startTime;
        this.completionTime = completionTime;
    }

    public String getPName() {
        return pName;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }
}
