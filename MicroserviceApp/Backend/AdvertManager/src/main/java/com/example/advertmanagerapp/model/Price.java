package com.example.advertmanagerapp.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="Price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String priceName;

    @Column
    private float pricePerDay;

    @Column
    private float insurancePrice;

    @Column
    private float distanceOverflowPrice;

    //@Max(1)
    //@Min(0)
    @Column
    private float discount;

    @Column
    private boolean isRemoved;

    @OneToMany(mappedBy = "price",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Advert> advertSet;

}
