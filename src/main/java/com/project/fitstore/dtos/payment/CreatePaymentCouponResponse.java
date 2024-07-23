package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.coupon.Coupon;

import java.util.UUID;

public record CreatePaymentCouponResponse(UUID couponId, String name, Double percentage) {
    public static CreatePaymentCouponResponse from(Coupon coupon){
        return new CreatePaymentCouponResponse(coupon.getId(), coupon.getName(), coupon.getPercentage());
    }
}
