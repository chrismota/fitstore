package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.payment.Payment;

import java.util.List;

public record PaymentListResponseDto(List<PaymentResponseDto> payments) {
    public static PaymentListResponseDto from(List<Payment> payments){
        return new PaymentListResponseDto(payments.stream().map(PaymentResponseDto::from).toList());
    }
}
