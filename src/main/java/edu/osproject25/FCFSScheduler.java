package edu.osproject25;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler {
    /**
     * Uses First Come First Serve to schedule a list of process objects
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
        }

        GanttChart.print(executionList);
    }
}
