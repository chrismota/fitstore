package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetOrderResponse(
        UUID id, BigDecimal totalValue,
        BigDecimal discountValue, BigDecimal totalWithDiscount,
        Status status, LocalDateTime createdAt, List<GetItemResponse> items) {
    public static GetOrderResponse from(Order order) {
        return new GetOrderResponse(
                order.getId(), order.getTotalValue(), order.getDiscountValue(),
                order.getTotalWithDiscount(), order.getStatus(), order.getCreatedAt(),
                order.getItems().stream().map(GetItemResponse::from).toList());
    }
}
