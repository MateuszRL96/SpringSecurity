package com.example.main.entity;

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
    private long productId;
    private String uid;
    private boolean active;
    private String name;
    private String mainDescription;
    private String descHtml;
    private float price;
    private Set<String> imageUrls;
    private Map<String, String> parameters;
    private LocalDate createDate;
    private PoziomTrudnosci poziomTrudnosci;
}
