package com.example.users.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @ElementCollection
    private List<String> options = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Form form;
}
