package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.CreateCouponRequest;
import com.project.fitstore.dtos.coupon.CreateCouponResponse;
import com.project.fitstore.dtos.coupon.UpdateCouponRequest;
import com.project.fitstore.dtos.coupon.UpdateCouponResponse;
import com.project.fitstore.services.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponController {
    final CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }

    @PutMapping("/coupons/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCoupon(@PathVariable("id") Long id, @RequestBody @Valid UpdateCouponRequest updateCouponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updateCouponRequest));
    }

    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable("id") Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}


