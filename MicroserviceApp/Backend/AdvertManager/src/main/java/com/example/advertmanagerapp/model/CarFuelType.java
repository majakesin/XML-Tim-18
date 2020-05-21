package com.example.advertmanagerapp.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="CarFuelType")
public class CarFuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
   // @Min(2)
    // @Max(32)
    private String fuelType;

    @Column
    private boolean isRemoved;

}
