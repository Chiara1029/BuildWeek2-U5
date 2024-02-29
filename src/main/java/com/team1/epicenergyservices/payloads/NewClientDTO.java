package com.team1.epicenergyservices.payloads;

import com.team1.epicenergyservices.enums.ClientType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewClientDTO(

        @NotEmpty
        ClientType companyType,

        @NotEmpty
        String companyName,
        @NotEmpty
        String pIva,
        @NotEmpty
        @Email
        String email,

        LocalDate lastContactDate,
        @NotNull
        double revenue,
        @NotEmpty
        String pec,
        @NotEmpty
        String telephone,
        @NotEmpty
        String emailContact,
        @NotEmpty
        String nameContact,
        @NotEmpty
        String surnameContact,
        @NotEmpty
        String numberContact,
        String logo,
        @NotNull
        AddressDTO legalAddress,

        AddressDTO operatingAddress

) {


}
