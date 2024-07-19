package com.project.fitstore.dtos.payment;

import com.project.fitstore.domain.coupon.Coupon;

import java.util.UUID;

public record CouponListResponseForPaymentDto(UUID couponId, String name, Double percentage) {
    public static CouponListResponseForPaymentDto from(Coupon coupon){
        return new CouponListResponseForPaymentDto(coupon.getId(), coupon.getName(), coupon.getPercentage());
    }
}
