package com.project.fitstore.controllers;

import com.project.fitstore.dtos.order.*;
import com.project.fitstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping
    public ResponseEntity<OrderListResponseDto> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("orderId")UUID orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderDto createOrderDto, @PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto, customerId), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestBody UpdateOrderStatusDto updateOrderStatusDto, @PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusDto, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder( @PathVariable("orderId") UUID orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
