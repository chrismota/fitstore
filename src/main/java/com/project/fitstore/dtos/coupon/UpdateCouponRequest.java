package com.project.fitstore.dtos.coupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateCouponRequest(
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
}
