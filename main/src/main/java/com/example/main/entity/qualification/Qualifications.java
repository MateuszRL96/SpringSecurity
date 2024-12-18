package com.example.main.entity.qualification;


import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "qualifications")
public class Qualifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Setter
    private List<Technologies> listOfTechnologies;


    public Qualifications(long id, String name, List<Technologies> listOfTechnologies) {
        this.id = id;
        this.name = name;
        this.listOfTechnologies = listOfTechnologies;
    }

    public Qualifications() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Technologies> getListOfTechnologies() {
        return listOfTechnologies;
    }

}
