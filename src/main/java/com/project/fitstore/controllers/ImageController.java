package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.services.ImageService;
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

    private final ImageService imageService;

    @PostMapping("/customer/{id}/upload")
    public ResponseEntity<String> uploadCustomerImage(@RequestParam("image") MultipartFile imageFile, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();

        return new ResponseEntity<>(imageService.uploadCustomerImage(imageFile, customer.getId()), HttpStatus.OK);
    }

    @PutMapping("/customer/{id}/update")
    public ResponseEntity<String> updateCustomerImage(@RequestParam("image") MultipartFile imageFile, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(imageService.updateCustomerImage(imageFile, customer.getId()),HttpStatus.OK);
    }


}
