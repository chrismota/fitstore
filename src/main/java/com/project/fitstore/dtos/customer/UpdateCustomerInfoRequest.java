package com.project.fitstore.dtos.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCustomerInfoRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        @Size(min = 5, max = 255, message = "Name must be between 5 to 255 characters")
        String name,
        String phoneNumber,
        @NotNull(message = "address is mandatory")
        @NotBlank(message = "address cannot be blank")
        @Size(min = 20, message = "Address must have at least 20 characters.")
        String address,
        @NotNull(message = "cpf is mandatory")
        @NotBlank(message = "cpf cannot be blank")
        String cpf,
        @NotNull(message = "email is mandatory")
        @NotBlank(message = "email cannot be blank")
        @Email(message = "Email should be valid")
        String email) {
}
