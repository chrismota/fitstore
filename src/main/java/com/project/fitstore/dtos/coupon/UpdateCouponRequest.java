package com.project.fitstore.dtos.coupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateCouponRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        String name,
        Double percentage,
        LocalDateTime startTime,
        LocalDateTime expirationTime,
        BigDecimal minValue) {

}
