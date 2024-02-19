package com.example.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Product {
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
