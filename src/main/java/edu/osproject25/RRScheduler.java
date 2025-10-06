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
        for (ProcessObj p : ps) rem.put(p.pid(), p.burst());

        // 3) Ready queue + execution timeline
        Queue<ProcessObj> q = new ArrayDeque<>();
        List<CPUState> executionList = new ArrayList<>();

        int time = 0, i = 0;

        while (i < ps.size() || !q.isEmpty()) {
            // Admit arrivals up to 'time'
            while (i < ps.size() && ps.get(i).arrival() <= time) q.add(ps.get(i++));

            // If no ready process, CPU is idle until next arrival
            if (q.isEmpty()) {
                int nextArr = ps.get(i).arrival();
                if (time < nextArr) {
                    executionList.add(new CPUState(-1, time, nextArr)); // IDLE with pid -1
                    time = nextArr;
                }
                continue;
            }

            // Run current process for up to 'quantum'
            ProcessObj cur = q.poll();
            int run = Math.min(quantum, rem.get(cur.pid()));
            int start = time, end = time + run;
            executionList.add(new CPUState(cur.pid(), start, end));
            time = end;
            rem.put(cur.pid(), rem.get(cur.pid()) - run);

            // Admit new arrivals that appeared during this slice
            while (i < ps.size() && ps.get(i).arrival() <= time) q.add(ps.get(i++));

            // Not finished? requeue
            if (rem.get(cur.pid()) > 0) q.add(cur);
        }

        // 4) Print outputs here (scheduler-owned printing)
        GanttChart.print(executionList);
        Metrics.print(Metrics.compute(procs, executionList));
    }
}
