package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetCouponResponse(Long id, String name, String code,
                                Double percentage, LocalDateTime startTime,
                                LocalDateTime expirationTime,
                                BigDecimal minValue) {
    public static GetCouponResponse from(Coupon coupon) {
        return new GetCouponResponse(coupon.getId(), coupon.getName(), coupon.getCode(),
                coupon.getPercentage(), coupon.getStartTime(),
                coupon.getExpirationTime(), coupon.getMinValue()
        );
    }
}

