package com.project.fitstore.controllers;

import com.project.fitstore.dtos.payment.GetAllPaymentsResponse;
import com.project.fitstore.dtos.payment.GetPaymentResponse;
import com.project.fitstore.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminPaymentController {
    final PaymentService paymentService;

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

}
