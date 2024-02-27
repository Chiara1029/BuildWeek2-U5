package com.team1.epicenergyservices.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "Username required.")
        @Size(min = 5, max = 20, message = "Username must be long from 5 to 20 characters")
        String username,

        @NotEmpty(message = "Email required.")
        @Email(message = "Invalid email.")
        String email,

        @NotEmpty(message = "Password required.")
        @Size(min = 5, max = 20, message = "Password must have at least 20 characters.")
        String password,

        @NotEmpty(message = "Name required.")
        @Size(min = 2, max = 20, message = "Name must have from2 to 20 characters.")
        String name,

        @NotEmpty(message = "Last name required.")
        @Size(min = 2, max = 20, message = "Last name must have from2 to 20 characters.")
        String lastName
) {
}
