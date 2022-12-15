package org.example.models;

import java.util.List;

public class Person {
    static Integer IDMan = 0;
    static Integer IDWoman = 0;
    public Integer id;
    String name;
    public List<Integer> rang;
    public Person partner;
    public Person(String ime, List<Integer> rang, Boolean isWoman) {
        if(isWoman) {
            this.id = IDWoman++;
        }
        else {
            this.id = IDMan++;
        }
        this.name = ime;
        this.rang = rang;
        this.partner = null;
    }
    @Override
    public String toString() {
        String output = name;
        if(partner != null) {
            output += " married with " + partner.name;
        }
        else {
            output += " no partner";
        }
        return output + "\n";
    }
}
