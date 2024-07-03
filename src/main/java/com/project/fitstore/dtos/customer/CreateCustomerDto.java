package com.project.fitstore.dtos.customer;

public record CreateCustomerDto(String name, String phoneNumber, String address, String cpf, String email, String password) {
}
