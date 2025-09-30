package edu.osproject25;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler {

    public static void schedule(List<ProcessObj> p) {
        List<ProcessObj> processes = ProcessSort.byArrivalTime(p);
        List<CPUState> executionList = new ArrayList<>();
        int time = 0;

        for (ProcessObj process: processes) {
            String pName = String.format("P%s", process.getPid());
            time += process.getBurstTime();
            CPUState state = new CPUState(pName, time);
            executionList.add(state);
        }
    }
}
