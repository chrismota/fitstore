package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentOrderResponse(UUID id, BigDecimal totalValue, BigDecimal discount, BigDecimal finalValue) {

    public static CreatePaymentOrderResponse from(Order order){
        return new CreatePaymentOrderResponse(order.getId(), order.getFullValue(), order.getDiscount(), order.getValueAfterDiscount());
    }
}
