package com.example.main.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProgressId;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private LocalDateTime startDate;
}
