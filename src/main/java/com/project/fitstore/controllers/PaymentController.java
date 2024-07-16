package com.project.fitstore.controllers;

import com.project.fitstore.dtos.payment.CreatePaymentDto;
import com.project.fitstore.dtos.payment.PaymentListResponseDto;
import com.project.fitstore.dtos.payment.PaymentResponseDto;
import com.project.fitstore.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<PaymentListResponseDto> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable("paymentId")UUID paymentId){
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreatePaymentDto createPaymentDto){
        return new ResponseEntity<>(paymentService.createPayment(createPaymentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment( @PathVariable("paymentId") UUID paymentId){
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }
}
