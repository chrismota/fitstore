package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.order.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(UUID id, BigDecimal discount, BigDecimal total, Status status, List<ItemResponseDto> items) {
    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order.getId(), order.getDiscount(), order.getTotal(), order.getStatus(), order.getItems().stream().map(ItemResponseDto::from).toList());
    }
}
