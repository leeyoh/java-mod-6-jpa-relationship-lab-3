package org.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cats {
    public Cats(String name, String breed, int age){
        this.age = age;
        this.name = name;
        this.breed = breed;
    }
    @Id
    @GeneratedValue
    private int id;
    private String name, breed;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @ManyToMany
    private List<Owners> owners = new ArrayList<>();
    public void addOwners(Owners owner) {
        owners.add(owner);
    }

}
