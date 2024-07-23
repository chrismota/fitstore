package com.project.fitstore.controllers;

import com.project.fitstore.dtos.payment.CreatePaymentRequest;
import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.dtos.payment.CreatePaymentResponse;
import com.project.fitstore.dtos.payment.GetPaymentResponse;
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
    public ResponseEntity<GetAllPaymentsResponse> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    @GetMapping("{id}")
    public ResponseEntity<GetPaymentResponse> getPayment(@PathVariable("id")UUID id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest createPaymentRequest){
        return new ResponseEntity<>(paymentService.createPayment(createPaymentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") UUID id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
