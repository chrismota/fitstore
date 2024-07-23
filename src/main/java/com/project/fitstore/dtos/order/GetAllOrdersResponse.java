package com.project.fitstore.dtos.order;

import com.project.fitstore.domain.order.Order;

import java.util.List;

public record GetAllOrdersResponse(List<GetOrderResponse> orders) {
    public static GetAllOrdersResponse from(List<Order> orders){
        return new GetAllOrdersResponse(orders.stream().map(GetOrderResponse::from).toList());
    }
}
