package com.project.fitstore.dtos.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateCouponRequest(String name, Double percentage, LocalDateTime startTime, LocalDateTime expirationTime,
                                  BigDecimal minValue) {

}
