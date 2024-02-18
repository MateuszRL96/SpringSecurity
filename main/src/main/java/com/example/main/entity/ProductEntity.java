package com.example.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends Product{
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


    public ProductEntity(long productId, String uid, boolean active, String name, String mainDescription, String descHtml, float price, String[] imageUrls,
                         String parameters, LocalDate createDate, PoziomTrudnosci poziomTrudnosci, Category category) {
        super(productId, uid, active, name, mainDescription, descHtml, price, imageUrls, parameters, createDate, poziomTrudnosci);
        this.category = category;
    }
}
