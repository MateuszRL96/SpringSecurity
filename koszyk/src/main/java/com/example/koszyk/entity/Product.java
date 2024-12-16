package com.example.koszyk.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private long id;
    private String uid;
    private boolean activate;
    private String name;
    private String mainDescription;
    private String descHtml;
    private float price;
    private String[] imageUrl;
    private String parameters;
    private LocalDate createAt;
    private Category category;
}
