package com.example.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "product_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_id_seq",sequenceName = "product_id_seq", allocationSize = 1)
    private long productId;
    private String uid;
    private boolean active;
    @Column(name = "product_name")
    private String name;
    private String mainDescription;
    private String descHtml;
    private float price;
    private String[] imageUrls;
    private String parameters;
    private LocalDate createDate;
    private PoziomTrudnosci poziomTrudnosci;
}
