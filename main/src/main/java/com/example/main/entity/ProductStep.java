package com.example.main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stepId;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private String stepContent;
    private String materials;
    private int numberStep;
}
