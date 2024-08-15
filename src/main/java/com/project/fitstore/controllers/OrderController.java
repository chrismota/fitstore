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
