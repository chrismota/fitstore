package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.*;
import com.project.fitstore.services.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    final CouponService couponService;

    @GetMapping
    public ResponseEntity<GetAllCouponsResponse> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCouponResponse> getCoupon(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(couponService.getCoupon(id));
    }
}


