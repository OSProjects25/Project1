package edu.osproject25;

import java.util.ArrayList;
import java.util.List;

/**
 * First-Come, First-Served (FCFS) scheduler implementation.
 * Produces a single non-preemptive slice per process in arrival order.
 */
public class FCFSScheduler {
    /**
     * Schedules processes using FCFS and prints a Gantt chart and metrics.
     * This method computes start/completion times, turnaround and waiting times,
     * and then delegates printing to {@link GanttChart} and {@link Metrics}.
     *
     * @param p unsorted list of processes to schedule
     */
    public static void schedule(List<ProcessObj> p) {
        List<ProcessObj> processes = ProcessSort.byArrivalTime(p);
        List<CPUState> executionList = new ArrayList<>();
        int currentTime = 0;

        for (ProcessObj process: processes) {
            String pName = String.format("P%s", process.getPid());
            int startTime = Math.max(currentTime, process.getArrivalTime());
            int completionTime = startTime + process.getBurstTime();
            currentTime = completionTime;

            CPUState state = new CPUState(pName, startTime, completionTime);
            executionList.add(state);

            process.setCompletionTime(completionTime);
            process.setTurnaroundTime(completionTime - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
        }
        
        GanttChart.print(executionList);
        Metrics.print(processes);
    }
}
