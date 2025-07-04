package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.customer.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateCustomerRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        @Size(min = 5, max = 255, message = "Name must be between 5 to 255 characters")
        String name,
        @NotNull(message = "phone number is mandatory")
        @NotBlank(message = "phone number cannot be blank")
        String phoneNumber,
        @NotNull(message = "street is mandatory")
        @NotBlank(message = "street cannot be blank")
        String street,
        @NotNull(message = "houseNumber is mandatory")
        @NotBlank(message = "houseNumber cannot be blank")
        String houseNumber,
        String complement,
        @NotNull(message = "neighborhood is mandatory")
        @NotBlank(message = "neighborhood cannot be blank")
        String neighborhood,
        @NotNull(message = "city is mandatory")
        @NotBlank(message = "city cannot be blank")
        String city,
        @NotNull(message = "state is mandatory")
        @NotBlank(message = "state cannot be blank")
        String state,
        @NotNull(message = "cep is mandatory")
        @NotBlank(message = "cep cannot be blank")
        String cep,
        @NotNull(message = "cpf is mandatory")
        @NotBlank(message = "cpf cannot be blank")
        String cpf,
        @NotNull(message = "email is mandatory")
        @NotBlank(message = "email cannot be blank")
        @Email(message = "Email should be valid")
        String email,
        @NotNull(message = "password is mandatory")
        @NotBlank(message = "password cannot be blank")
        @Size(min = 6, message = "Password must have at least 6 characters.")
        String password,
        @NotNull(message = "role is mandatory")
        Role role) {
    public Customer toCustomer(String password) {
        return new Customer(null, name, phoneNumber, street,
                houseNumber, complement, neighborhood,
                city, state, cep, cpf, email, password, role,
                null, LocalDateTime.now(), LocalDateTime.now());
    }
}