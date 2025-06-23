package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.GetAllCouponsResponse;
import com.project.fitstore.dtos.coupon.GetCouponResponse;
import com.project.fitstore.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    final CouponService couponService;

    @GetMapping
    public ResponseEntity<GetAllCouponsResponse> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/status")
    public ResponseEntity<GetAllCouponsResponse> getCouponsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(couponService.getCouponsByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCouponResponse> getCoupon(@PathVariable("id") Long id) {
        return ResponseEntity.ok(couponService.getCoupon(id));
    }
}
