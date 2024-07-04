package com.project.fitstore.controllers;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.dtos.order.CreateOrderDto;
import com.project.fitstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/{customerId}")
    public ResponseEntity<Order> createOrder(CreateOrderDto createOrderDto, @PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto, customerId), HttpStatus.CREATED);
    }

}
