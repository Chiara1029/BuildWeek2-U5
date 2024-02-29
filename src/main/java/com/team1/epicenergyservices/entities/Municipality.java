package com.team1.epicenergyservices.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "municipalities")
@Getter
@Setter
@NoArgsConstructor
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private int id;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    private String municipality;

    public Municipality(Province province, String municipality) {
        this.province = province;
        this.municipality = municipality;
    }
}
