package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.time.LocalDateTime;

public record CreateCustomerDto(String name, String phoneNumber, String address, String cpf, String email, String password) {
    public Customer toCustomer() {
        return new Customer(null, name, phoneNumber, address, cpf, email, password, LocalDateTime.now(), LocalDateTime.now());
    }
}