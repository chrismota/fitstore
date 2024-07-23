package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.OrderItem.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateItemResponse(UUID productId, String productName, Integer quantity, BigDecimal unityPrice) {
    public static CreateItemResponse from(OrderItem orderItem){
        return new CreateItemResponse(orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                orderItem.getQuantity(), orderItem.getUnityPrice());
    }
}
