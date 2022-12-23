package org.example.models;

import java.util.List;

public class Person {
    public int id;
    String name;
    public List<Integer> rang;
    public Person partner;
    public Person(String ime, List<Integer> rang, Integer id) {
        this.id = id;
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
