package com.example.advertmanagerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CarBrand")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    //@Min(2)
    //@Max(32)
    private String name;

    @Column
    private boolean isRemoved;

    @OneToMany
    private List<CarModel> carModelSet;

}