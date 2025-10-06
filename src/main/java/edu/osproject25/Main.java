package edu.osproject25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the project");

        String processes = "processes.txt";
        List<ProcessObj> processData = ProcessUtils.ReadProcessInfo(processes);

        if (args.length > 0 && args[0].equalsIgnoreCase("rr")) {
            int q = (args.length > 1) ? Integer.parseInt(args[1]) : 2;
            System.out.println("Running Round Robin, quantum=" + q);
            RRScheduler.schedule(processData, q);   // prints internally
        } else {
            System.out.println("Running FCFS");
            FCFSScheduler.schedule(processData);    // prints internally
        }
    }
}
