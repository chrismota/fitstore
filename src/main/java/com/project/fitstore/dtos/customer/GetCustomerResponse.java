package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.util.UUID;

public record GetCustomerResponse(
        UUID id, String name,
        String phoneNumber, String street,
        String houseNumber, String complement,
        String neighborhood, String city,
        String state, String cep,
        String cpf, String email, String imagePath) {
    public static GetCustomerResponse from(Customer customer) {
        return new GetCustomerResponse(
                customer.getId(), customer.getName(), customer.getPhoneNumber(),
                customer.getStreet(), customer.getHouseNumber(), customer.getComplement(),
                customer.getNeighborhood(), customer.getCity(), customer.getState(),
                customer.getCep(), customer.getCpf(),
                customer.getEmail(), customer.getImagePath());
    }
}
