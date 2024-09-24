package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.domain.payment.Status;
import com.project.fitstore.dtos.payment.CreatePaymentRequest;
import com.project.fitstore.dtos.payment.CreatePaymentResponse;
import com.project.fitstore.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(
            @RequestBody @Valid CreatePaymentRequest createPaymentRequest, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        CreatePaymentResponse createPaymentResponse = paymentService.createPayment(createPaymentRequest,
                customer.getId());
        return new ResponseEntity<>(createPaymentResponse,
                createPaymentResponse.status().equals(Status.SUCCESS) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

}
