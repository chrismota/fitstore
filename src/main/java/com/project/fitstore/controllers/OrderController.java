package com.project.fitstore.controllers;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.dtos.order.CreateOrderDto;
import com.project.fitstore.dtos.order.OrderResponseDto;
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
    @PostMapping("/{customerId}")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderDto createOrderDto, @PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto, customerId), HttpStatus.CREATED);
    }

}
