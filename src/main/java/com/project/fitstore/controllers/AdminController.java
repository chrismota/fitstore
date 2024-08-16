package com.project.fitstore.controllers;

import com.project.fitstore.dtos.coupon.CreateCouponRequest;
import com.project.fitstore.dtos.coupon.CreateCouponResponse;
import com.project.fitstore.dtos.coupon.UpdateCouponRequest;
import com.project.fitstore.dtos.coupon.UpdateCouponResponse;
import com.project.fitstore.dtos.customer.GetAllCustomersResponse;
import com.project.fitstore.dtos.customer.GetCustomerResponse;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoRequest;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoResponse;
import com.project.fitstore.dtos.order.GetAllOrdersResponse;
import com.project.fitstore.dtos.order.GetOrderResponse;
import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.dtos.payment.GetPaymentResponse;
import com.project.fitstore.dtos.product.CreateProductRequest;
import com.project.fitstore.dtos.product.CreateProductResponse;
import com.project.fitstore.dtos.product.UpdateProductRequest;
import com.project.fitstore.dtos.product.UpdateProductResponse;
import com.project.fitstore.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    final OrderService orderService;
    final PaymentService paymentService;
    final ProductService productService;
    final CustomerService customerService;
    final CouponService couponService;

    @GetMapping("/orders")
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/payments")
    public ResponseEntity<GetAllPaymentsResponse> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<GetPaymentResponse> getPayment(@PathVariable("paymentId") UUID paymentId) {
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }

    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable("paymentId") UUID paymentId){
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable("id") UUID id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, updateProductRequest));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers")
    public ResponseEntity<GetAllCustomersResponse> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<UpdateCustomerInfoResponse> updateCustomerInfo(@PathVariable("id") UUID id, @RequestBody @Valid UpdateCustomerInfoRequest updateCustomerInfoRequest) {
        return ResponseEntity.ok(customerService.updateCustomerInfo(id, updateCustomerInfoRequest));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/coupons")
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest createCouponRequest) {
        return new ResponseEntity<>(couponService.createCoupon(createCouponRequest), HttpStatus.CREATED);
    }

    @PutMapping("/coupons/{id}")
    public ResponseEntity<UpdateCouponResponse> updateCoupon(@PathVariable("id") UUID id, @RequestBody @Valid UpdateCouponRequest updateCouponRequest) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updateCouponRequest));
    }

    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<Void> updateCoupon(@PathVariable("id") UUID id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}
