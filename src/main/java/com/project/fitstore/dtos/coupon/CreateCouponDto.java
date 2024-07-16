package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.order.Order;
import com.project.fitstore.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCouponDto(String name, Double percentage, LocalDateTime startTime, LocalDateTime expirationTime,
                              BigDecimal minValue, UUID paymentId) {
    public Coupon toCoupon(){
        Payment payment = Payment.builder().id(paymentId).build();
        return Coupon.builder().id(null).name(name).percentage(percentage).startTime(startTime)
                .expirationTime(expirationTime)
                .minValue(minValue)
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
