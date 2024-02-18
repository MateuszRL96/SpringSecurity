package com.example.main.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private float ocenaUsera;
    private float sredniaOcena;
    private LocalDateTime ratingDate;
}
