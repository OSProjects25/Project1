package edu.osproject25;

import java.util.*;

public final class Metrics {
    public static class Result {
        public final Map<Integer,Integer> waiting = new HashMap<>();
        public final Map<Integer,Integer> turnaround = new HashMap<>();
        public double avgWT, avgTAT, cpuUtil;
    }

    // Assumes CPUState(pid,start,end) and pid==-1 for IDLE slices
    public static Result compute(List<ProcessObj> procs, List<CPUState> timeline) {
        Result r = new Result();

        Map<Integer,Integer> arrival = new HashMap<>();
        Map<Integer,Integer> burst   = new HashMap<>();
        for (ProcessObj p : procs) {
            arrival.put(p.pid(), p.arrival());
            burst.put(p.pid(), p.burst());
        }

        Map<Integer,Integer> completion = new HashMap<>();
        int busy = 0, makespan = 0;

        for (CPUState s : timeline) {
            makespan = Math.max(makespan, s.end());
            if (s.pid() != -1) {
                completion.put(s.pid(), s.end());
                busy += (s.end() - s.start());
            }
        }

        int n = procs.size();
        long sumWT = 0, sumTAT = 0;

        for (ProcessObj p : procs) {
            int comp = completion.get(p.pid());
            int tat = comp - p.arrival();   // Turnaround = completion - arrival
            int wt  = tat - p.burst();      // Waiting = turnaround - burst
            r.turnaround.put(p.pid(), tat);
            r.waiting.put(p.pid(), wt);
            sumWT += wt; sumTAT += tat;
        }

        r.avgWT  = sumWT  / (double) n;
        r.avgTAT = sumTAT / (double) n;
        r.cpuUtil = makespan == 0 ? 0.0 : (busy * 100.0) / makespan;
        return r;
    }

    public static void print(Result r) {
        System.out.println("\nPer-process metrics:");
        r.waiting.keySet().stream().sorted().forEach(pid ->
            System.out.printf("P%-3d  WT=%-4d  TAT=%-4d%n",
                pid, r.waiting.get(pid), r.turnaround.get(pid)));
        System.out.printf("%nAverage WT=%.2f   Average TAT=%.2f   CPU Utilization=%.1f%%%n",
                r.avgWT, r.avgTAT, r.cpuUtil);
    }
}
