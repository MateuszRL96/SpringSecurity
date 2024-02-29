package com.example.main.entity.people;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="people_table")
public class People {
    private long id;
    private String name;
    private String lastName;
    private String address;
    private int age;
    private String phone;
}
