package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderDto(List<CreateItemDto> products) {

    public Order toOrder(UUID customerId){
        Customer customer = Customer.builder().id(customerId).build();
        return Order.builder().id(null).status(Status.CREATED).customer(customer).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
    }
}
