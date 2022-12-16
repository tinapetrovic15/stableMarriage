package org.example.models;

import java.io.FileWriter;
import java.io.IOException;

public class WorkerThred implements Runnable{

    private String message;

    private static String pathForOutput = "src/main/java/org/example/files/stableMarriage.txt";
    public static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter(pathForOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StableMarriage stableMarriage;

    public WorkerThred(String message, StableMarriage stableMarriage) {
        this.message = message;
        this.stableMarriage = stableMarriage;
    }
    @Override
    public void run() {
        stableMarriage.findAllCouples();
        stableMarriage.printAllCouples();
        try {
            stableMarriage.printAllSolution(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
