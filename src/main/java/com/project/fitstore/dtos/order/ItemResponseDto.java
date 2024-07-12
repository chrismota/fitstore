package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.OrderItem.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponseDto(UUID productId, String productName, Integer quantity, BigDecimal unityPrice) {
    public static ItemResponseDto from(OrderItem orderItem){
        return new ItemResponseDto(orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                orderItem.getQuantity(), orderItem.getUnityPrice());
    }
}
