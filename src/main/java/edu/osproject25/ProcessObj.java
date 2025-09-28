package edu.osproject25;

public class ProcessObj {
    private int pid;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    
    public ProcessObj(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", priority=" + priority +
                '}';
    }
}