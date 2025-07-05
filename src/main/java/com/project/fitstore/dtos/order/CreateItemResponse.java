package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.OrderItem.OrderItem;

import java.math.BigDecimal;

public record CreateItemResponse(
        Long id, String productName,
        BigDecimal unityPrice, Integer quantity,
        BigDecimal total, String imagePath) {
    public static CreateItemResponse from(OrderItem orderItem) {
        return new CreateItemResponse(
                orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                orderItem.getUnityPrice(), orderItem.getQuantity(),
                orderItem.getTotal(), orderItem.getProduct().getImagePath());
    }
}
