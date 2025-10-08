package edu.osproject25;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessSort {

    /**
     * Returns a new list sorted by ascending arrival time.
     *
     * @param p input processes (not modified)
     * @return new list sorted by {@link ProcessObj#getArrivalTime()}
     */
    public static List<ProcessObj> byArrivalTime(List<ProcessObj> p){
        List<ProcessObj> sorted = new ArrayList<>(p);
        sorted.sort(Comparator.comparing(ProcessObj::getArrivalTime));
        return sorted;
    }

}
