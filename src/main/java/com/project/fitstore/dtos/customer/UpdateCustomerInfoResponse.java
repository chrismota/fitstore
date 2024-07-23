package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.util.UUID;

public record UpdateCustomerInfoResponse(UUID id, String name, String phoneNumber, String address,
                                         String cpf, String email) {

    public static UpdateCustomerInfoResponse from(Customer customer) {
        return new UpdateCustomerInfoResponse(customer.getId(), customer.getName(), customer.getPhoneNumber(),
                customer.getAddress(), customer.getCpf(), customer.getEmail());
    }
}
