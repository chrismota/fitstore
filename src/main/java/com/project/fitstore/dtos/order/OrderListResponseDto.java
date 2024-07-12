package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;

import java.util.List;

public record OrderListResponseDto(List<OrderResponseDto> orders) {
    public static OrderListResponseDto from(List<Order> orders){
        return new OrderListResponseDto(orders.stream().map(OrderResponseDto::from).toList());
    }
}
