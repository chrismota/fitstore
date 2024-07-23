package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderResponse(UUID id, BigDecimal discount, BigDecimal total, Status status, List<CreateItemResponse> items) {
    public static CreateOrderResponse from(Order order) {
        return new CreateOrderResponse(order.getId(), order.getDiscount(), order.getTotal(), order.getStatus(), order.getItems().stream().map(CreateItemResponse::from).toList());
    }
}
