package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;

import java.time.LocalDateTime;
import java.util.UUID;
public record CreatePaymentDto (UUID orderId, Method method){
    public Payment toPayment(){
        Order order = Order.builder().id(orderId).build();
        return Payment.builder().id(null)
                .method(method)
                .order(order)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
