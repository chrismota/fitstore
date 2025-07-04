package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.coupon.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateCouponResponse(
        Long id, String name, Status status, String code,
        Double percentage, LocalDateTime startTime,
        LocalDateTime expirationTime, BigDecimal minValue) {
    public static CreateCouponResponse from(Coupon coupon) {
        return new CreateCouponResponse(
                coupon.getId(), coupon.getName(),
                coupon.getStatus(), coupon.getCode(),
                coupon.getPercentage(), coupon.getStartTime(),
                coupon.getExpirationTime(), coupon.getMinValue()
        );
    }
}

