package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @UniqueElements(message = "You cannot add the same product twice")
        List<CreateItemRequest> products) {

    public Order toOrder(UUID customerId, LocalDateTime expiresAt){
        Customer customer = Customer.builder().id(customerId).build();
        return Order.builder().id(null).status(Status.CREATED).customer(customer)
                .expiresAt(expiresAt)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
