package com.team1.epicenergyservices.entities;


import com.team1.epicenergyservices.enums.ClientType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Client {


    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String pIva;
    @Column(nullable = false)
    private String email;
    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;
    @Column(name = "last_contact_date", nullable = false)
    private LocalDate lastContactDate;
    @Column(nullable = false)
    private double revenue;
    @Column(nullable = false)
    private String pec;
    @Column(nullable = false)
    private String telephone;
    @Column(name = "contact_email", nullable = false)
    private String emailContact;
    @Column(name = "contact_name", nullable = false)
    private String nameContact;
    @Column(name = "contact_surname", nullable = false)
    private String surnameContact;
    @Column(name = "contact_number", nullable = false)
    private String numberContact;
    @Column(nullable = false)
    private String logo;
    @Column(name = "company_type", nullable = false)
    private ClientType companyType;
    @Column(name = "legal_address", nullable = false)
    private Address legalAddress;
    @Column(name = "operating_address")
    private Address operatingAddress;

    private List<Invoice> invoiceList;

    public Client(String companyName, String pIva, String email, LocalDate registerDate, LocalDate lastContactDate, double revenue, String pec, String telephone, String emailContact, String nameContact, String surnameContact, String numberContact, String logo, ClientType companyType, Address legalAddress) {
        this.companyName = companyName;
        this.pIva = pIva;
        this.email = email;
        this.registerDate = registerDate;
        this.lastContactDate = lastContactDate;
        this.revenue = revenue;
        this.pec = pec;
        this.telephone = telephone;
        this.emailContact = emailContact;
        this.nameContact = nameContact;
        this.surnameContact = surnameContact;
        this.numberContact = numberContact;
        this.logo = logo;
        this.companyType = companyType;
        this.legalAddress = legalAddress;
    }
}
