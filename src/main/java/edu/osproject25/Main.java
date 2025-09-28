package edu.osproject25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the project");
        String processes = "processes.txt";
        List<ProcessObj> processData = ProcessUtils.ReadProcessInfo(processes);
        System.out.println(processData);
    }
}
