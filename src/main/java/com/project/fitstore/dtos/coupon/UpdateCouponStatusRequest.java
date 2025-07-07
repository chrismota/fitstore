package com.project.fitstore.dtos.coupon;

import com.project.fitstore.domain.coupon.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateCouponStatusRequest(@NotNull Status status) {
}
