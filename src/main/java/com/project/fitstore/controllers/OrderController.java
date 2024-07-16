package com.project.fitstore.controllers;

import com.project.fitstore.dtos.order.CreateOrderDto;
import com.project.fitstore.dtos.order.OrderListResponseDto;
import com.project.fitstore.dtos.order.OrderResponseDto;
import com.project.fitstore.dtos.order.UpdateOrderStatusDto;
import com.project.fitstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    final OrderService orderService;
    @GetMapping
    public ResponseEntity<OrderListResponseDto> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("paymentId")UUID orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderDto createOrderDto, @PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto, customerId), HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestBody UpdateOrderStatusDto updateOrderStatusDto, @PathVariable("paymentId") UUID orderId){
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusDto, orderId));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deleteOrder( @PathVariable("paymentId") UUID orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
