package com.team1.epicenergyservices.payloads;

import com.team1.epicenergyservices.enums.ClientType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientUpdateDTO(

        @NotBlank
        ClientType clientType,

        @NotBlank
        String pIva,

        @Email
        String email,

        LocalDate lastContactDate,

        @NotBlank
        double revenue,

        @NotBlank
        String pec,

        @NotBlank
        String telephone,

        @NotBlank
        String contatcEmail,

        @NotBlank
        String contactName,

        @NotBlank
        String contactSurname,

        @NotBlank
        String contactNumber,

        @NotBlank
        String logo


) {
}
