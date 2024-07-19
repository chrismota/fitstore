package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCouponDto(String name, Double percentage, LocalDateTime startTime, LocalDateTime expirationTime,
                              BigDecimal minValue, UUID paymentId) {
    public Coupon toCoupon(){
        return Coupon.builder().id(null).name(name).percentage(percentage).startTime(startTime)
                .expirationTime(expirationTime)
                .minValue(minValue)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
