package com.project.fitstore.dtos.customer;

public record UpdateCustomerInfoRequest(String name, String phoneNumber, String address, String cpf, String email) {
}
