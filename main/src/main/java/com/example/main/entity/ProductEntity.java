package com.example.main.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "product")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity extends Product{
    @Id
    @GeneratedValue(generator = "product_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_id_seq",sequenceName = "product_id_seq", allocationSize = 1)
    private long productId;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    public ProductEntity(long productId, String uid, boolean active, String name, String mainDescription, String descHtml, float price, String[] imageUrls,
                         String parameters, LocalDate createDate, PoziomTrudnosci poziomTrudnosci, Category category) {
        super(uid, active, name, mainDescription, descHtml, price, imageUrls, parameters, createDate, poziomTrudnosci);
        this.category = category;
        this.productId = productId;
    }
}
