package org.example;
import org.example.models.StableMarriage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String pathForMen = "src/main/java/org/example/files/men.txt";
        String pathForWomen = "src/main/java/org/example/files/women.txt";
        String pathForOutput = "src/main/java/org/example/files/stableMarriage.txt";

        StableMarriage stableMarrige = new StableMarriage(pathForWomen, pathForMen);
        stableMarrige.findCouple();
        stableMarrige.printCouple();
        stableMarrige.printSolutionInFile(pathForOutput);

    }
}