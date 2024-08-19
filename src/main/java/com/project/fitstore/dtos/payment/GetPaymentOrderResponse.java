package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record GetPaymentOrderResponse(UUID id, BigDecimal totalValue, BigDecimal discount, BigDecimal finalValue) {

    public static GetPaymentOrderResponse from(Order order){
        return new GetPaymentOrderResponse(order.getId(), order.getFullValue(), order.getDiscount(), order.getValueAfterDiscount());
    }
}
