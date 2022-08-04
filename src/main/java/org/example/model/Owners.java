package org.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owners {
    public Owners(String name){
        this.name = name;
    }
    @Id
    @GeneratedValue
    private int id;
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "owners")
    private List<Cats> cats = new ArrayList<>();
    public void addCats(Cats cat) {
        cats.add(cat);
    }

}
