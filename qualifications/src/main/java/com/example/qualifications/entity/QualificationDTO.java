package com.example.qualifications.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class QualificationDTO {
    private String uuid;
    private String orders;
    private Status status;
    private CustomDetails customDetails;
    private RabbitConnectionDetails.Address address;
    private static DeliverDTO deliver;
    private List<Items> items;
    private double summaryPrice;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CustomDetails getCustomDetails() {
        return customDetails;
    }

    public void setCustomDetails(CustomDetails customDetails) {
        this.customDetails = customDetails;
    }

    public RabbitConnectionDetails.Address getAddress() {
        return address;
    }

    public void setAddress(RabbitConnectionDetails.Address address) {
        this.address = address;
    }

    public static DeliverDTO getDeliver() {
        return deliver;
    }

    public void setDeliver(DeliverDTO deliver) {
        this.deliver = deliver;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}

