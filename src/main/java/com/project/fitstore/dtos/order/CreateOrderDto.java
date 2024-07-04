package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderDto(List<CreateOrderProductsDto> products) {

    public Order toOrder(UUID customerId){
        Customer customer = Customer.builder().id(customerId).build();
        return new Order(null, aggregatedValue, discountValue, finalValue, Status.CREATED, customer, products, LocalDateTime.now(), LocalDateTime.now());
    }
}
