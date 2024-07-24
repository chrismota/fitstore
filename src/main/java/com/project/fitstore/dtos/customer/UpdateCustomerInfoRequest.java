package com.project.fitstore.dtos.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCustomerInfoRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        @Size(min = 5, max = 100, message = "Name must be between 5 to 100 characters")
        String name,
        String phoneNumber,
        String address,
        String cpf,
        @NotNull(message = "email is mandatory")
        @NotBlank(message = "email cannot be blank")
        @Email(message = "Email should be valid")
        String email) {
}
