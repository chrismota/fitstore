package com.project.fitstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.order.CreateOrderRequest;
import com.project.fitstore.dtos.order.CreateOrderResponse;
import com.project.fitstore.dtos.order.GetAllOrdersResponse;
import com.project.fitstore.dtos.order.GetOrderResponse;
import com.project.fitstore.dtos.order.UpdateOrderStatusRequest;
import com.project.fitstore.dtos.order.UpdateOrderStatusResponse;
import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.services.OrderService;
import com.project.fitstore.services.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<GetOrderResponse> getOrderFromCustomer(@PathVariable("orderId") UUID orderId,
            Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(orderService.getOrderFromCustomer(orderId, customer.getId()));
    }

    @GetMapping("/{orderId}/payments")
    public ResponseEntity<GetAllPaymentsResponse> getPaymentFromOrder(@PathVariable("orderId") UUID orderId,
            Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(paymentService.getAllPaymentsFromOrder(orderId, customer.getId()));
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest,
            Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest, customer.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(
            @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest, @PathVariable("orderId") UUID orderId,
            Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(orderService.updateOrderStatus(updateOrderStatusRequest, orderId, customer.getId()));
    }
}
