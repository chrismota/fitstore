package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record GetOrderResponse(UUID id, BigDecimal discount, BigDecimal total, Status status, List<GetItemResponse> items) {
    public static GetOrderResponse from(Order order) {
        return new GetOrderResponse(order.getId(), order.getDiscount(), order.getTotal(), order.getStatus(), order.getItems().stream().map(GetItemResponse::from).toList());
    }
}
