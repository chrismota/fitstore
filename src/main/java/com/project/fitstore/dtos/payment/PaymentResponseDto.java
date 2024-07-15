package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponseDto(UUID id, Method method, UUID orderId, BigDecimal orderTotal) {
    public static PaymentResponseDto from(Payment payment){
        return new PaymentResponseDto(payment.getId(), payment.getMethod(), payment.getOrder().getId(), payment.getOrder().getTotal());
    }
}

