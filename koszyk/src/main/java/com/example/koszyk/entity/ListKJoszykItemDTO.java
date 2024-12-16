package com.example.koszyk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListKJoszykItemDTO {
    private List<KoszykItemDTO> koszykProducts;
    private double summaryPrice;
}
