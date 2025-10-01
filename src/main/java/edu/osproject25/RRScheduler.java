package edu.osproject25;

import java.util.*;

public final class RRScheduler {
    public static List<CPUState> schedule(List<ProcessObj> procs, int quantum) {
        if (quantum <= 0) throw new IllegalArgumentException("quantum must be > 0");

        // Sort arrivals (deterministic)
        List<ProcessObj> ps = new ArrayList<>(procs);
        ps.sort(Comparator.comparingInt(ProcessObj::arrival)
                          .thenComparingInt(ProcessObj::pid));

        // Remaining time per PID
        Map<Integer,Integer> rem = new HashMap<>();
        for (ProcessObj p : ps) rem.put(p.pid(), p.burst());

        Queue<ProcessObj> q = new ArrayDeque<>();
        List<CPUState> timeline = new ArrayList<>();
        int time = 0, i = 0;

        while (i < ps.size() || !q.isEmpty()) {
            while (i < ps.size() && ps.get(i).arrival() <= time) q.add(ps.get(i++));
            if (q.isEmpty()) {
                int nextArr = ps.get(i).arrival();
                if (time < nextArr) {
                    timeline.add(CPUState.idle(time, nextArr)); // assume helper; else new CPUState(-1,time,nextArr)
                    time = nextArr;
                }
                continue;
            }

            ProcessObj cur = q.poll();
            int run = Math.min(quantum, rem.get(cur.pid()));
            int start = time, end = time + run;
            timeline.add(new CPUState(cur.pid(), start, end));
            time = end;
            rem.put(cur.pid(), rem.get(cur.pid()) - run);

            // admit arrivals during the slice
            while (i < ps.size() && ps.get(i).arrival() <= time) q.add(ps.get(i++));

            if (rem.get(cur.pid()) > 0) q.add(cur); // unfinished → back of queue
        }
        return timeline;
    }
}
