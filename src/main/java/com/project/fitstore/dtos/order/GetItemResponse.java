package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.OrderItem.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record GetItemResponse(UUID id, String productName, Integer quantity, BigDecimal unityPrice) {
    public static GetItemResponse from(OrderItem orderItem){
        return new GetItemResponse(orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                orderItem.getQuantity(), orderItem.getUnityPrice());
    }
}
