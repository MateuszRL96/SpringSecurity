package com.example.qualifications.entity.notify;


import com.example.qualifications.entity.Status;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Qualification {
    private String qualificationId;
    private String extQualificationId;
    private String qualificationCreateDate;
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private String totalAmount;
    private Buyer buyer;
    private List<Product> products;
    private Status status;

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getExtQualificationId() {
        return extQualificationId;
    }

    public void setExtQualificationId(String extQualificationId) {
        this.extQualificationId = extQualificationId;
    }

    public String getqualificationCreateDate() {
        return qualificationCreateDate;
    }

    public void setqualificationCreateDate(String qualificationCreateDate) {
        this.qualificationCreateDate = qualificationCreateDate;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getMerchantPosId() {
        return merchantPosId;
    }

    public void setMerchantPosId(String merchantPosId) {
        this.merchantPosId = merchantPosId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

