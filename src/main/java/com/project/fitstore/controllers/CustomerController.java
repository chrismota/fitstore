package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.CreateCustomerDto;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoDto;
import com.project.fitstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id")UUID id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto createCustomerDto){
        return new ResponseEntity<>(customerService.createCustomer(createCustomerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerInfo(@PathVariable("id") UUID id,@RequestBody UpdateCustomerInfoDto updateCustomerInfoDto){
        return ResponseEntity.ok(customerService.updateCustomerInfo(id, updateCustomerInfoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") UUID id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
