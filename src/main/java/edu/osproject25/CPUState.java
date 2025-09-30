package edu.osproject25;

public class CPUState {
    private String pName;
    private int executionTime;

    public CPUState(String pName, int executionTime) {
        this.pName = pName;
        this.executionTime = executionTime;
    }

    public String getPName() {
        return pName;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return String.format("Process name: %s, Time: %d", pName, executionTime);
    }
}
