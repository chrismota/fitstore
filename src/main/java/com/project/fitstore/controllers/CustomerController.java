package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.CreateCustomerRequest;
import com.project.fitstore.dtos.customer.CreateCustomerResponse;
import com.project.fitstore.dtos.customer.GetCustomerResponse;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoRequest;
import com.project.fitstore.dtos.customer.UpdateCustomerPasswordRequest;
import com.project.fitstore.dtos.customer.UpdateCustomerResponse;
import com.project.fitstore.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    final CustomerService customerService;

    @GetMapping
    public ResponseEntity<GetCustomerResponse> getCustomer(Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(customerService.getCustomer(customer.getId()));
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/info")
    public ResponseEntity<UpdateCustomerResponse> updateCustomerInfo(
            Authentication auth, @Valid @RequestBody UpdateCustomerInfoRequest updateCustomerInfoRequest) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(customerService.updateCustomerInfo(customer.getId(), updateCustomerInfoRequest));
    }

    @PutMapping("/password")
    public ResponseEntity<UpdateCustomerResponse> updateCustomerPassword(
            Authentication auth, @Valid @RequestBody UpdateCustomerPasswordRequest updateCustomerPasswordRequest) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(customerService.updateCustomerPassword(customer.getId(), updateCustomerPasswordRequest));
    }
}
