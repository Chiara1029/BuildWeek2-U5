package com.team1.epicenergyservices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
}
