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
    @ManyToOne
    private UUID invoiceId;
    private LocalDate date;
    private Long number;
    private Long invoiceValue;
    private String invoiceState;

    public Invoice(LocalDate date, Long number, Long invoiceValue, String invoiceState) {
        this.date = date;
        this.number = number;
        this.invoiceValue = invoiceValue;
        this.invoiceState = invoiceState;
    }
}

