package org.example.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StableMarriage {
    private List<Person> boys;
    private List<Person> girls;

    public StableMarriage(String pathForWomen, String pathForMen) throws FileNotFoundException {

        girls = new ArrayList<Person>();
        boys = new ArrayList<Person>();

        inputFromFile(girls,pathForWomen, true);
        inputFromFile(boys, pathForMen, false);
    }
    private void inputFromFile(List<Person> people, String nameOfFile, boolean isWoman) throws FileNotFoundException {

        File myObj = new File(nameOfFile);
        Scanner myReader = new Scanner(myObj);

        if (myReader.hasNext()){
            String data = myReader.nextLine();
            Integer numOfPeople = Integer.parseInt(data);
            for (int i = 0; i < numOfPeople; i++){
                String name = myReader.nextLine();
                List<Integer> rang = new ArrayList<Integer>();
                for(int j = 0; j < numOfPeople; j++ ) {
                    Integer womenID = Integer.parseInt(myReader.nextLine());
                    rang.add(j,womenID);
                }
                people.add(new Person(name,rang,isWoman));
            }
        }
        myReader.close();
    }
    private boolean womenPrefersOtherManOverFiance(Person woman, Person fiance, Person otherMan){
        for(Integer rangMan : woman.rang){
            if(woman.rang.get(rangMan) == otherMan.id)
                return true;
            if(woman.rang.get(rangMan) == fiance.id)
                return false;
        }
        return false;
    }
    private Person getFirstFreeMan(List<Person> men) {
        for (Person man : men){
            if(man.partner == null)
                return man;
        }
        return null;
    }
    private void findCouple(List<Person> men, List<Person> women){

        Person freeMan = getFirstFreeMan(men);

        while (freeMan != null) {
            for(Integer womenId : freeMan.rang){
                if(women.get(womenId).partner == null){
                    women.get(womenId).partner = freeMan;
                    men.get(freeMan.id).partner = women.get(womenId);
                }
                else {
                    Person fiance = women.get(womenId).partner;
                    if(!womenPrefersOtherManOverFiance(women.get(womenId),freeMan, fiance)){
                        men.get(freeMan.id).partner = women.get(womenId);
                        women.get(womenId).partner = freeMan;
                        men.get(fiance.id).partner = null;
                    }
                }
                if(men.get(freeMan.id).partner != null) break;
            }
            freeMan = getFirstFreeMan(men);
        }
    }
    private void printCouple(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }
    private void printSolutionInFile(FileWriter fileWriter, List<Person> people) throws IOException {
        for (Person person : people) {
            fileWriter.write(String.valueOf(person));
        }
        fileWriter.close();
    }
    public void findCouple(){
        findCouple(boys, girls);
    }
    public void printCouple(){
        printCouple(girls);
    }
    public void printSolutionInFile(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        printSolutionInFile(fileWriter, girls);
    }
}
