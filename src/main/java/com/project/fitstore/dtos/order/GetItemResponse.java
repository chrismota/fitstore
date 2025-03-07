package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.OrderItem.OrderItem;

import java.math.BigDecimal;

public record GetItemResponse(Long id, String productName, Integer quantity, BigDecimal unityPrice, String imagePath) {
    public static GetItemResponse from(OrderItem orderItem) {
        return new GetItemResponse(orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                orderItem.getQuantity(), orderItem.getUnityPrice(), orderItem.getProduct().getImagePath());
    }
}
