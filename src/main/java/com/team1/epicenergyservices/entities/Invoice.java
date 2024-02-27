package com.team1.epicenergyservices.entities;

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
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID invoiceId;
    private LocalDate date;
    private long number;
    private long invoiceValue;
    private String invoiceState;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(LocalDate date, long number, long invoiceValue, String invoiceState) {
        this.date = date;
        this.number = number;
        this.invoiceValue = invoiceValue;
        this.invoiceState = invoiceState;
    }
}