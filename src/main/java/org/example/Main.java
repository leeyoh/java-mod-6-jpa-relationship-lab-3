package org.example;

import org.example.model.Cats;
import org.example.model.Owners;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // access transaction object
        EntityTransaction transaction = entityManager.getTransaction();

        // create objects
        List<Cats> catsList = new ArrayList<Cats>(){{
            add(new Cats("Tom","Tabby",2));
            add(new Cats("Jerry","British Short Hair",4));
            add(new Cats("Garry","A",3));
            add(new Cats("Joe","B",22));
        }};
        List<Owners> ownersList = new ArrayList<Owners>(){{
            add(new Owners("Sabrina"));
            add(new Owners("John"));
            add(new Owners("Latoya"));
        }};

        //John Owns all the Cats
        ownersList.get(1).addCats(catsList.get(1));
        ownersList.get(1).addCats(catsList.get(0));
        ownersList.get(1).addCats(catsList.get(2));
        ownersList.get(1).addCats(catsList.get(3));

        catsList.get(0).addOwners(ownersList.get(1));
        catsList.get(1).addOwners(ownersList.get(1));
        catsList.get(2).addOwners(ownersList.get(1));
        catsList.get(3).addOwners(ownersList.get(1));

        //LaToya Owns some Cats
        ownersList.get(2).addCats(catsList.get(3));


        catsList.get(2).addOwners(ownersList.get(2));
        catsList.get(3).addOwners(ownersList.get(2));

        //Sab Owns one Cats
        ownersList.get(0).addCats(catsList.get(1));
        catsList.get(1).addOwners(ownersList.get(0));

        transaction.begin();
        ownersList.stream().forEach((owner) -> {
            entityManager.persist(owner);
        });
        catsList.stream().forEach((cat) -> {
            entityManager.persist(cat);
        });

        transaction.commit();
        // close entity manager
        entityManager.close();
        entityManagerFactory.close();
    }
}