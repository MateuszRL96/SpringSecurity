package com.example.koszyk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class KoszykItems {
    @Id
    @GeneratedValue(generator = "increment")
    @SequenceGenerator(name = "increment", sequenceName = "increment", allocationSize = 1)
    private Long id;
    private String uuid;
    private String product;
    @ManyToOne
    @JoinColumn(name = "koszyk_id")
    private Koszyk koszyk;
    private long quantity;
}
