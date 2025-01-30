package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final CustomerService customerService;

    @PostMapping("/customer/upload")
    public ResponseEntity<String> uploadCustomerImage(@RequestParam("image") MultipartFile imageFile, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();

        return new ResponseEntity<>(customerService.uploadCustomerImage(imageFile, customer.getId()), HttpStatus.OK);
    }

    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomerImage(@RequestParam("image") MultipartFile imageFile, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(customerService.updateCustomerImage(imageFile, customer.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{fileName}/delete")
    public ResponseEntity<String> deleteCustomerImage(@PathVariable String fileName, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(customerService.deleteCustomerImage(fileName, customer.getId()), HttpStatus.OK);
    }
}
