package com.project.fitstore.controllers;

import com.project.fitstore.dtos.order.*;
import com.project.fitstore.services.OrderService;
import com.project.fitstore.services.PaymentService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, @PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest, customerId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest, @PathVariable("id") UUID id) {
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
