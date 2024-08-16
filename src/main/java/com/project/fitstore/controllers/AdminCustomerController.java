package com.project.fitstore.controllers;

import com.project.fitstore.dtos.customer.GetAllCustomersResponse;
import com.project.fitstore.dtos.customer.GetCustomerResponse;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoRequest;
import com.project.fitstore.dtos.customer.UpdateCustomerInfoResponse;
import com.project.fitstore.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCustomerController {
    final CustomerService customerService;

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
}
