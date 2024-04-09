package com.example.users.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();
}
