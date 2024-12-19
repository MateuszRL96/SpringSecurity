package com.example.qualifications.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class QualificationItems {
    @Id
    @GeneratedValue(generator = "order_items_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_items_id_seq",sequenceName = "order_items_id_seq", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "orders")
    private Qualification qualification;
    private String product;
    private String uuid;
    private String name;
    private long quantity;
    @Column(name = "priceunit")
    private double priceUnit;
    @Column(name = "pricesummary")
    private double priceSummary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public static void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(double priceUnit) {
        this.priceUnit = priceUnit;
    }

    public double getPriceSummary() {
        return priceSummary;
    }

    public void setPriceSummary(double priceSummary) {
        this.priceSummary = priceSummary;
    }
}
