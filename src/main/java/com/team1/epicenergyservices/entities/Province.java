package com.team1.epicenergyservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private int id;
    private String provinceCode;
    private String provinceName;
    @JsonIgnore
    @OneToMany(mappedBy = "province")
    private Set<Municipality> municipalities;

    public Province(String provinceCode, String provinceName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }
}
