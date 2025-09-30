package edu.osproject25;

import java.util.List;

public class GanttChart {
    /**
     * Prints a Gantt Chart of the execution order
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
