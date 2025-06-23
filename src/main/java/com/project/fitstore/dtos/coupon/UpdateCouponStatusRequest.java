package com.project.fitstore.dtos.coupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCouponStatusRequest(
        @NotNull(message = "status is mandatory")
        @NotBlank(message = "status cannot be blank") String status) {
}
