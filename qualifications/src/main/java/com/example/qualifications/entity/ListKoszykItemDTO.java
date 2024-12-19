package com.example.qualifications.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ListKoszykItemDTO {
    private List<KoszykItemDTO> koszykProducts;
    private double summaryPrice;

    public List<KoszykItemDTO> getKoszykProducts() {
        return koszykProducts;
    }

    public void setKoszykProducts(List<KoszykItemDTO> koszykProducts) {
        this.koszykProducts = koszykProducts;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}

