# Project 1 - CPU Scheduling Simulator

## Overview
This Maven-based Java project simulates a first-come, first-served (FCFS) CPU scheduler. The current implementation reads a list of processes from `src/main/resources/processes.txt`, orders them by arrival time, computes execution windows, and prints a simple Gantt chart to the console.

## Implemented Functionality
- Process ingestion via `ProcessUtils.ReadProcessInfo`, which loads `processes.txt` from the classpath and converts each row into a `ProcessObj`.
- Lightweight `ProcessObj` model describing PID, arrival time, burst time, and priority for each process.
- Deterministic sorting helper (`ProcessSort.byArrivalTime`) to guarantee FCFS order.
- FCFS scheduling in `FCFSScheduler.schedule`, which walks the sorted list, tracks CPU time, accounts for idle gaps, and creates `CPUState` records for the timeline.
- Text-mode Gantt chart (`GanttChart.print`) that renders the execution order and timestamps, inserting `IDLE` segments whenever the CPU is waiting for the next arrival.
- `Main` entry point that wires everything together and kicks off the simulation.

## Input Data
The simulator expects a whitespace-delimited file with four columns: PID, arrival time, burst time, and priority. The included sample lives at `src/main/resources/processes.txt`; feel free to add or edit rows to model different workloads. The parser ignores the header row and assumes all remaining lines are well-formed integers.

## Building and Running
1. Compile the project (`maven` or `javac`). Examples:
   - `mvn clean compile`
   - `javac -d target/classes src/main/java/edu/osproject25/*.java`
2. Launch the simulator (resources are on the runtime classpath once compiled):
   - `java -cp target/classes edu.osproject25.Main`

You should see output similar to the following for the bundled dataset:
```
Starting the project
| P1 | P2 | P3 |
0    5    8   10
```

## Next Steps
Planned enhancements include implementing additional scheduling algorithms, calculating turnaround/waiting times, and persisting results for analysis. Update this README as new features land.
