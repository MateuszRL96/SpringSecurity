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
public class Koszyk {
    @Id
    @GeneratedValue(generator = "increment")
    @SequenceGenerator(name = "increment", sequenceName = "increment", allocationSize = 1)
    private Long id;
    private String uuid;

}
