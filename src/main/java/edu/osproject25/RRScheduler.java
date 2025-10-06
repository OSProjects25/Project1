package edu.osproject25;

import java.util.*;

public final class RRScheduler {

    // Prints Gantt + metrics internally; returns nothing
    public static void schedule(List<ProcessObj> procs, int quantum) {
        if (quantum <= 0) throw new IllegalArgumentException("quantum must be > 0");

        // 1) Use shared helper to sort by arrival time
        List<ProcessObj> ps = ProcessSort.byArrivalTime(procs);

        // 2) Remaining burst per PID
        Map<Integer,Integer> rem = new HashMap<>();
        for (ProcessObj p : ps) rem.put(p.getPid(), p.getBurstTime());

        // 3) Ready queue + execution timeline
        Queue<ProcessObj> q = new ArrayDeque<>();
        List<CPUState> executionList = new ArrayList<>();

        int time = 0, i = 0;

        while (i < ps.size() || !q.isEmpty()) {
            // Admit arrivals up to 'time'
            while (i < ps.size() && ps.get(i).getArrivalTime() <= time) q.add(ps.get(i++));

            // If no ready process, CPU is idle until next arrival
            if (q.isEmpty()) {
                int nextArr = ps.get(i).getArrivalTime();
                if (time < nextArr) {
                    executionList.add(new CPUState("IDLE", time, nextArr));
                    time = nextArr;
                }
                continue;
            }

            // Run current process for up to 'quantum'
            ProcessObj cur = q.poll();
            int run = Math.min(quantum, rem.get(cur.getPid()));
            int start = time, end = time + run;
            executionList.add(new CPUState(String.format("P%s", cur.getPid()), start, end));
            time = end;
            rem.put(cur.getPid(), rem.get(cur.getPid()) - run);

            // Admit new arrivals that appeared during this slice
            while (i < ps.size() && ps.get(i).getArrivalTime() <= time) q.add(ps.get(i++));

            // Not finished? requeue
            if (rem.get(cur.getPid()) > 0) q.add(cur);
        }

        // 4) Calculate completion times from execution timeline
        for (ProcessObj process : ps) {
            String processName = String.format("P%s", process.getPid());
            int completionTime = 0;
            
            // Find the last execution slice for this process
            for (CPUState state : executionList) {
                if (state.getPName().equals(processName)) {
                    completionTime = state.getCompletionTime();
                }
            }
            
            process.setCompletionTime(completionTime);
            process.setTurnaroundTime(completionTime - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
        }

        // 5) Print outputs here (scheduler-owned printing)
        GanttChart.print(executionList);
        Metrics.print(ps);
    }
}
