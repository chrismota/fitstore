package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateCouponRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotNull(message = "code is mandatory")
        @NotBlank(message = "code cannot be blank")
        String code,
        Double percentage,
        LocalDateTime startTime,
        LocalDateTime expirationTime,
    BigDecimal minValue) {
    public Coupon toCoupon(){
        return Coupon.builder().id(null).name(name).code(code).percentage(percentage).startTime(startTime)
                .expirationTime(expirationTime)
                .minValue(minValue)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
