package com.project.fitstore.controllers;

import com.project.fitstore.dtos.order.*;
import com.project.fitstore.services.OrderService;
import com.project.fitstore.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    final OrderService orderService;
    final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest, @PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest, customerId), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest, @PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusRequest, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
