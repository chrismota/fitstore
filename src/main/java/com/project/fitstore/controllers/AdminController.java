package com.project.fitstore.controllers;

import com.project.fitstore.dtos.order.GetAllOrdersResponse;
import com.project.fitstore.dtos.order.GetOrderResponse;
import com.project.fitstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
}
