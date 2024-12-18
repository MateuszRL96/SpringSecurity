package com.example.qualifications.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QualificationDTO {
    private String uuid;
    private String orders;
    private Status status;
    private CustomDetails customDetails;
    private RabbitConnectionDetails.Address address;
    private DeliverDTO deliver;
    private List<Items> items;
    private double summaryPrice;
}

