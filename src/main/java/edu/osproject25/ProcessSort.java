package edu.osproject25;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessSort {

    /**
     * This function sorts a list of process objects `p` by their arrival time and returns a sorted list
     **/
    public static List<ProcessObj> byArrivalTime(List<ProcessObj> p){
        List<ProcessObj> sorted = new ArrayList<>(p);
        sorted.sort(Comparator.comparing(ProcessObj::getArrivalTime));
        return sorted;
    }

}
