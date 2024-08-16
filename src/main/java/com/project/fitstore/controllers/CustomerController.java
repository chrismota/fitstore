package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.*;
import com.project.fitstore.dtos.order.GetAllOrdersResponse;
import com.project.fitstore.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UpdateCustomerInfoResponse> updateCustomerInfo(Authentication auth, @RequestBody @Valid UpdateCustomerInfoRequest updateCustomerInfoRequest) {
        var customer = (Customer) auth.getPrincipal();
        return ResponseEntity.ok(customerService.updateCustomerInfo(customer.getId(), updateCustomerInfoRequest));
    }
}
