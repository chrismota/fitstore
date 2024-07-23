package com.project.fitstore.dtos.customer;

import com.project.fitstore.domain.customer.Customer;

import java.util.List;

public record GetAllCustomersResponse(List<GetCustomerResponse> customers) {
    public static GetAllCustomersResponse from(List<Customer> customerList){
        return new GetAllCustomersResponse(customerList.stream().map(GetCustomerResponse::from).toList());
    }
}
