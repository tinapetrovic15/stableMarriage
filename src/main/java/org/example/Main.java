package org.example;
import org.example.models.StableMarriage;
import org.example.models.WorkerThred;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {

        String pathForMen = "src/main/java/org/example/files/men.txt";
        String pathForWomen = "src/main/java/org/example/files/women.txt";

        String pathForMen2 = "src/main/java/org/example/files/men2.txt";
        String pathForWomen2 = "src/main/java/org/example/files/women2.txt";

        String pathForMen3 = "src/main/java/org/example/files/men3.txt";
        String pathForWomen3 = "src/main/java/org/example/files/women3.txt";


        StableMarriage stableMarriage = new StableMarriage(pathForWomen, pathForMen);
        StableMarriage stableMarriage2 = new StableMarriage(pathForWomen2, pathForMen2);
        StableMarriage stableMarriage3 = new StableMarriage(pathForWomen3, pathForMen3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable worker = new WorkerThred("stable marrige",stableMarriage);
        Runnable worker2 = new WorkerThred("stable marrige2",stableMarriage2);
        Runnable worker3 = new WorkerThred("stable marrige3",stableMarriage3);

        executorService.execute(worker);
        executorService.execute(worker2);
        executorService.execute(worker3);

        executorService.shutdown();

        while (!executorService.isTerminated()) {}

        WorkerThred.fileWriter.close();
        System.out.println("Finished all threads");

    }
}