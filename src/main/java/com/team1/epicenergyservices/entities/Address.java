package com.team1.epicenergyservices.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private int id;
    private String street;
    private int civicNumber;
    private String location;
    private int cap;
    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    public Address(String street, int civicNumber, String location, int cap, Municipality municipality) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.location = location;
        this.cap = cap;
        this.municipality = municipality;
    }
}
