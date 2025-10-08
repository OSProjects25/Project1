# Project 1 - CPU Scheduling Simulator

## Overview
Maven-based Java project simulating CPU scheduling algorithms with a simple CLI. It loads processes from `src/main/resources/processes.txt`, executes a selected algorithm, and prints a Gantt chart and basic metrics.

Currently implemented:
- FCFS (First-Come, First-Served)
- RR (Round Robin, user-provided time quantum)

## Implemented Functionality
- Process ingestion via `ProcessUtils.ReadProcessInfo` (classpath resource → `ProcessObj` list).
- `ProcessObj` model with PID, arrival, burst, priority, plus computed metrics.
- Sorting helper `ProcessSort.byArrivalTime`.
- FCFS in `FCFSScheduler.schedule` (non-preemptive).
- Round Robin in `RRScheduler.schedule` (preemptive with quantum, handles idle gaps).
- Text-mode Gantt chart via `GanttChart.print` (inserts `IDLE` when appropriate).
- Metrics via `Metrics.print` (per-process WT/TAT and averages).
- `Main` CLI to select the algorithm and (for RR) prompt for quantum.

## Input Data
The simulator expects a whitespace-delimited file with four columns: `PID ARRIVAL BURST PRIORITY`. The included sample is at `src/main/resources/processes.txt`. The first line is treated as a header; subsequent lines should contain integers.

## Building and Running
1. Compile the project:
   - `mvn clean compile`
   - or `javac -d target/classes src/main/java/edu/osproject25/*.java`
2. Run the CLI (after compiling):
   - `java -cp target/classes edu.osproject25.Main`

You will be prompted to choose an algorithm:
- Enter `1` for FCFS
- Enter `2` for Round Robin (you can then provide a positive integer quantum)

Example output shape:
```
========== CPU Scheduling Simulator ==========
Loaded N processes from processes.txt

| P1 | P2 | IDLE| P3 |
0    5     9    10   14

Process P1: WT = 0, TAT = 5
Process P2: WT = 2, TAT = 7
...
Average Waiting Time: 3.25
Average Turnaround Time: 8.50
```

## Notes
- All times share the same unit (e.g., milliseconds, ticks). Choose any consistent unit for input.
- For RR, the quantum must be a positive integer.
