package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;

import java.util.UUID;

public record PaymentResponseDto(UUID id, Method method) {
    public static PaymentResponseDto from(Payment payment){
        return new PaymentResponseDto(payment.getId(), payment.getMethod());
    }
}

