package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponseForPaymentDto(UUID orderId, BigDecimal orderTotal) {

    public static OrderResponseForPaymentDto from(Order order){
        return new OrderResponseForPaymentDto(order.getId(), order.getTotal());
    }
}
