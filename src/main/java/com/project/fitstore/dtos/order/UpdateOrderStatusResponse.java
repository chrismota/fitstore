package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateOrderStatusResponse(UUID id, BigDecimal discount, BigDecimal totalValue, Status status) {

    public static UpdateOrderStatusResponse from(Order order) {
        return new UpdateOrderStatusResponse(order.getId(), order.getDiscount(), order.getFullValue(), order.getStatus());
    }
}
