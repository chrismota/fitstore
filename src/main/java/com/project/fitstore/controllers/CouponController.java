package com.project.fitstore.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitstore.dtos.coupon.GetAllCouponsResponse;
import com.project.fitstore.dtos.coupon.GetCouponResponse;
import com.project.fitstore.services.CouponService;

import lombok.RequiredArgsConstructor;

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
