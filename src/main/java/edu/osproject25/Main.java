package edu.osproject25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String processes = "processes.txt";
        List<ProcessObj> processData = ProcessUtils.ReadProcessInfo(processes);

        System.out.println("First Come First Serve Scheduling:");
        FCFSScheduler.schedule(processData);
    }
}
