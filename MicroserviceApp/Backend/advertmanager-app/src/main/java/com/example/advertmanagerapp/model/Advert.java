package com.example.advertmanagerapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="Advert")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private OwnersCar ownersCar;

    @Column(name="profilePicture")
    private String profilePicture;

    @OneToMany(mappedBy = "advert",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Picture> pictureSet;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private ClientCopy client;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="startOfAdvert")
    private Date startOfAdvert;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="endOfAdvert")
    private Date endOfAdvert;

    @Column(name="description")
    private String description;

    @Column(name="isActive")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Price price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "email")
    private ClientCopy currentDriver;



}