package com.example.qualifications.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListKoszykItemDTO {
    private List<KoszykItemDTO> basketProducts;
    private double summaryPrice;
}

