package edu.osproject25;

import java.util.List;

public class Metrics {
    public static void print(List<ProcessObj> processes) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int n = processes.size();

        for (ProcessObj process : processes) {
            System.out.printf("Process P%d: Waiting Time(WT) = %d, Turnaround Time(TAT) = %d%n",
                    process.getPid(), process.getWaitingTime(), process.getTurnaroundTime());
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        System.out.printf("Average Waiting Time: %.2f%n", totalWaitingTime / n);
        System.out.printf("Average Turnaround Time: %.2f%n", totalTurnaroundTime / n);
    }
}
