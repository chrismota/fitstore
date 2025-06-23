package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.domain.coupon.Status;
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
        @NotNull(message = "percentage is mandatory")
        Double percentage,
        @NotNull(message = "startTime is mandatory")
        LocalDateTime startTime,
        @NotNull(message = "expirationTime is mandatory")
        LocalDateTime expirationTime,
        @NotNull(message = "minValue is mandatory")
        BigDecimal minValue) {
    public Coupon toCoupon() {
        return Coupon.builder().id(null).name(name).status(Status.VALID).code(code).percentage(percentage).startTime(startTime)
                .expirationTime(expirationTime)
                .minValue(minValue)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
