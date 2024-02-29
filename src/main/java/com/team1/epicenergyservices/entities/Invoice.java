package com.team1.epicenergyservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long invoiceId;
    private LocalDate date;
    private double invoiceValue;
    private String invoiceState;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(LocalDate date, double invoiceValue, String invoiceState, Client client) {
        this.date = date;
        this.invoiceValue = invoiceValue;
        this.invoiceState = invoiceState;
        this.client = client;
    }
}