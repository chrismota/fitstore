package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PaymentResponseDto(UUID id,
                                 Method method, Status status,
                                 OrderResponseForPaymentDto order,
                                 List<CouponListResponseForPaymentDto> coupons) {
    public static PaymentResponseDto from(Payment payment){
        return new PaymentResponseDto(payment.getId(), payment.getMethod(), payment.getStatus(),
                OrderResponseForPaymentDto.from(payment.getOrder()),
                payment.getCoupons().stream().map(CouponListResponseForPaymentDto::from).toList());
    }

    public static PaymentResponseDto from(Payment payment, Order order){
        return new PaymentResponseDto(payment.getId(), payment.getMethod(), payment.getStatus(),
                OrderResponseForPaymentDto.from(order),
                payment.getCoupons().stream().map(CouponListResponseForPaymentDto::from).toList());
    }
}

