package com.project.fitstore.controllers;

import com.project.fitstore.dtos.customer.*;
import com.project.fitstore.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    final CustomerService customerService;

    @GetMapping
    public ResponseEntity<GetAllCustomersResponse> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("id")UUID id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCustomerInfoResponse> updateCustomerInfo(@PathVariable("id") UUID id, @RequestBody UpdateCustomerInfoRequest updateCustomerInfoRequest){
        return ResponseEntity.ok(customerService.updateCustomerInfo(id, updateCustomerInfoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
