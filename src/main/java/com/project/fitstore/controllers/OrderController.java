package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.order.*;
import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.services.OrderService;
import com.project.fitstore.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    final OrderService orderService;
    final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<GetAllOrdersResponse> getAllOrdersFromCustomer(Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(orderService.getAllOrdersFromCustomer(customer.getId()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrderFromCustomer(@PathVariable("orderId") UUID orderId, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(orderService.getOrderFromCustomer(orderId, customer.getId()));
    }

    @GetMapping("/{orderId}/payments")
    public ResponseEntity<GetAllPaymentsResponse> getPaymentFromOrder(@PathVariable("orderId") UUID orderId, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(paymentService.getAllPaymentsFromOrder(orderId, customer.getId()));
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest, customer.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest, @PathVariable("orderId") UUID orderId, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusRequest, orderId, customer.getId()));
    }
}
