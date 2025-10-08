package edu.osproject25;

/**
 * Immutable representation of a single CPU execution slice.
 * Each slice is identified by a name (e.g., {@code P1} or {@code IDLE}) and has
 * an inclusive start timestamp and exclusive completion timestamp.
 */
public class CPUState {
    private String pName;
    private int startTime;
    private int completionTime;

    /**
     * Creates a CPU execution slice.
     *
     * @param pName          label of the slice (process name or {@code IDLE})
     * @param startTime      inclusive start time
     * @param completionTime exclusive end time
     */
    public CPUState(String pName, int startTime, int completionTime) {
        this.pName = pName;
        this.startTime = startTime;
        this.completionTime = completionTime;
    }

    public String getPName() {
        return pName;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }
}
