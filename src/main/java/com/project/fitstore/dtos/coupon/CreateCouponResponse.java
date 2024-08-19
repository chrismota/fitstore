package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCouponResponse(UUID id, String name, String code, Double percentage, LocalDateTime startTime,
                                   LocalDateTime expirationTime,
                                   BigDecimal minValue) {
    public static CreateCouponResponse from(Coupon coupon) {
        return new CreateCouponResponse(coupon.getId(), coupon.getName(), coupon.getCode(),
                coupon.getPercentage(), coupon.getStartTime(),
                coupon.getExpirationTime(), coupon.getMinValue()
        );
    }
}

