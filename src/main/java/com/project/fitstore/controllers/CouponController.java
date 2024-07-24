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

    @PostMapping
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCoupon(@PathVariable("id") UUID id, @RequestBody @Valid UpdateCouponRequest updateCouponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updateCouponRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateCoupon(@PathVariable("id") UUID id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }


}


