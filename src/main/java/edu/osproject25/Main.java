package edu.osproject25;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    /**
     * Launches the simulator. Accepts no special arguments; interaction occurs via stdin.
     */
    public static void main(String[] args) {
        System.out.println("Starting the project");

        // Load process list (keep your existing loader if different)
        String processes = "processes.txt";
        List<ProcessObj> processData = ProcessUtils.ReadProcessInfo(processes);

        // Simple CLI prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("========== CPU Scheduling Simulator ==========");
        System.out.println("Loaded " + processData.size() + " processes from " + processes);
        System.out.println("\nChoose a scheduling algorithm:");
        System.out.println("  1) FCFS (FIFO)");
        System.out.println("  2) Round Robin (RR)");
        System.out.print("Enter 1 or 2: ");

        String choice = sc.nextLine().trim().toLowerCase(Locale.ROOT);

        switch (choice) {
            case "2":
            case "rr":
            case "round robin":
                int q = 2; // default quantum
                System.out.print("Enter time quantum (positive integer, default 2): ");
                String qIn = sc.nextLine().trim();
                if (!qIn.isEmpty()) {
                    try {
                        int parsed = Integer.parseInt(qIn);
                        if (parsed > 0) q = parsed;
                    } catch (NumberFormatException ignored) { /* keep default */ }
                }
                System.out.println("Running Round Robin, quantum=" + q + "\n");
                RRScheduler.schedule(processData, q);     // prints internally
                break;

            case "1":
            case "fcfs":
            case "fifo":
            default:
                System.out.println("Running FCFS (FIFO)\n");
                FCFSScheduler.schedule(processData);       // prints internally
                break;
        }

        sc.close();
    }
}
