package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.coupon.Coupon;

import java.util.UUID;

public record GetPaymentCouponResponse(UUID couponId, String name, Double percentage) {
    public static GetPaymentCouponResponse from(Coupon coupon){
        return new GetPaymentCouponResponse(coupon.getId(), coupon.getName(), coupon.getPercentage());
    }
}
