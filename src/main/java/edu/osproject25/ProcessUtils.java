package edu.osproject25;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {
    /**
     * Returns a list of process objects from a text file
     * @return List of process objects
     */
    public static List<ProcessObj> ReadProcessInfo(String processList) {
        List<ProcessObj> pList = new ArrayList<>();
        InputStream inputStream = ProcessUtils.class.getClassLoader().getResourceAsStream(processList);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + processList);
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");

                if (parts.length >= 4) {
                    int pid = Integer.parseInt(parts[0]);
                    int arrivalTime = Integer.parseInt(parts[1]);
                    int burstTime = Integer.parseInt(parts[2]);
                    int priority = Integer.parseInt(parts[3]);

                    pList.add(new ProcessObj(pid, arrivalTime, burstTime, priority));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading process data: " + e.getMessage());
        }

        return pList;
    }
}
