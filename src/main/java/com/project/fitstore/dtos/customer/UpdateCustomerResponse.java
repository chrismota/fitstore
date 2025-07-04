package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.util.UUID;

public record UpdateCustomerResponse(
        UUID id, String name, String phoneNumber,
        String street, String houseNumber, String complement,
        String neighborhood, String city, String state, String cep,
        String cpf, String email, String imagePath) {
    public static UpdateCustomerResponse from(Customer customer) {
        return new UpdateCustomerResponse(
                customer.getId(), customer.getName(),
                customer.getPhoneNumber(), customer.getStreet(), customer.getHouseNumber(),
                customer.getComplement(), customer.getNeighborhood(),
                customer.getCity(), customer.getState(), customer.getCep(),
                customer.getCpf(), customer.getEmail(), customer.getImagePath());
    }
}
