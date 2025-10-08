package edu.osproject25;

import java.util.List;

public class GanttChart {
    /**
     * Prints a Gantt chart for the provided execution timeline.
     * The first line shows process segments (e.g., {@code | P1 | P2 | IDLE |}),
     * and the second line shows aligned timestamps for segment boundaries.
     *
     * @param executionList ordered list of execution slices to render
     */
    public static void print(List<CPUState> executionList) {

        StringBuilder pOrder = new StringBuilder("|");
        StringBuilder pTime = new StringBuilder();
        int i = 0;

        pOrder.append(String.format(" %s |", executionList.get(0).getPName()));
        pTime.append(executionList.get(i).getStartTime());
        int prevEnd = executionList.get(i).getCompletionTime();
        i++;

        while (i < executionList.size()){
            CPUState curr = executionList.get(i);
            if (prevEnd < curr.getStartTime()) {
                pOrder.append("IDLE|");
                pTime.append(String.format("%5d", prevEnd));
            }
            pOrder.append(String.format(" %s |", curr.getPName()));
            pTime.append(String.format("%5d", curr.getStartTime()));
            prevEnd = curr.getCompletionTime();
            i++;
        }
        pTime.append(String.format("%5d", executionList.get(i-1).getCompletionTime()));

        System.out.println(pOrder);
        System.out.println(pTime);
    }
    
}
