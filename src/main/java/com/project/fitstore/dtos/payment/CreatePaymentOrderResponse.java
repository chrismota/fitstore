package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentOrderResponse(UUID orderId, BigDecimal orderTotal) {

    public static CreatePaymentOrderResponse from(Order order){
        return new CreatePaymentOrderResponse(order.getId(), order.getTotal());
    }
}
