package com.project.fitstore.controllers;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.CreateCouponResponse;
import com.project.fitstore.dtos.coupon.CreateCouponRequest;
import com.project.fitstore.dtos.coupon.GetAllCouponsResponse;
import com.project.fitstore.dtos.coupon.GetCouponResponse;
import com.project.fitstore.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }
}
