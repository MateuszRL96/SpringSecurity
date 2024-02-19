package com.example.main.entity.dto;

import com.example.main.entity.PoziomTrudnosci;
import com.example.main.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends Product {
    private CategoryDTO categoryDTO;

    public ProductDTO(String uid, boolean active, String name, String mainDescription, String descHtml, float price,
                      String[] imageUrls, String parameters, LocalDate createDate, PoziomTrudnosci poziomTrudnosci, CategoryDTO categoryDTO) {
        super(uid, active, name, mainDescription, descHtml, price, imageUrls, parameters, createDate, poziomTrudnosci);
        this.categoryDTO = categoryDTO;
    }
}
