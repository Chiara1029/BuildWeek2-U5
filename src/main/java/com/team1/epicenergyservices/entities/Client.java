package com.team1.epicenergyservices.entities;


import com.team1.epicenergyservices.enums.ClientType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "clients")
public class Client {


    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "company_name")
    private String companyName;

    private String pIva;

    private String email;
    @Column(name = "register_date")
    private LocalDate registerDate;
    @Column(name = "last_contact_date")
    private LocalDate lastContactDate;

    private double revenue;

    private String pec;

    private String telephone;
    @Column(name = "contact_email")
    private String emailContact;
    @Column(name = "contact_name")
    private String nameContact;
    @Column(name = "contact_surname")
    private String surnameContact;
    @Column(name = "contact_number")
    private String numberContact;

    private String logo;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    private ClientType companyType;

    @OneToOne
    @JoinColumn(name = "address_id_legal")
    private Address legalAddress;

    @OneToOne
    @JoinColumn(name = "address_id_operating")
    private Address operatingAddress;

    @OneToMany(mappedBy = "client")
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
