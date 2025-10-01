package edu.osproject25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the project");

        // Load process list
        String processes = "processes.txt"; // already in resources
        List<ProcessObj> processData = ProcessUtils.ReadProcessInfo(processes);

        // Choose algorithm
        List<CPUState> timeline;
        if (args.length > 0 && args[0].equalsIgnoreCase("rr")) {
            int q = (args.length > 1) ? Integer.parseInt(args[1]) : 2;
            System.out.println("Running Round Robin, quantum=" + q);
            timeline = RRScheduler.schedule(processData, q);
        } else {
            System.out.println("Running FCFS");
            timeline = FCFSScheduler.schedule(processData);
        }

        // Print Gantt chart
        GanttChart.print(timeline);

        // Print metrics
        var result = Metrics.compute(processData, timeline);
        Metrics.print(result);
    }
}
