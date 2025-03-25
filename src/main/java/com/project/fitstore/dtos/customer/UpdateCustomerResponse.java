package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.util.UUID;

public record UpdateCustomerResponse(UUID id, String name, String phoneNumber, String address,
                                     String cpf, String email, String imagePath) {

    public static UpdateCustomerResponse from(Customer customer) {
        return new UpdateCustomerResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
                customer.getAddress(), customer.getCpf(), customer.getEmail(), customer.getImagePath());
    }
}
