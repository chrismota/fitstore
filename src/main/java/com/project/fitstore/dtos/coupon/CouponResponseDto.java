package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.payment.Method;
import com.project.fitstore.domain.payment.Payment;
import com.project.fitstore.domain.payment.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CouponResponseDto(UUID id, String name, Double percentage, LocalDateTime startTime,
                                LocalDateTime expirationTime,
                                BigDecimal minValue) {
    public static CouponResponseDto from(Coupon coupon) {
        return new CouponResponseDto(coupon.getId(), coupon.getName(), coupon.getPercentage(), coupon.getStartTime(),
                coupon.getExpirationTime(), coupon.getMinValue()
        );
    }
}

