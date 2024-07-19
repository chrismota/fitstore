package com.project.fitstore.controllers;

import com.project.fitstore.domain.coupon.Coupon;
import com.project.fitstore.dtos.coupon.CouponResponseDto;
import com.project.fitstore.dtos.coupon.CreateCouponDto;
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
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponseDto> getCoupon(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(couponService.getCoupon(id));
    }

    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody CreateCouponDto createCouponDto) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponDto), HttpStatus.CREATED);
    }
}
