package com.project.fitstore.controllers;

import com.project.fitstore.domain.customer.Customer;
import com.project.fitstore.dtos.customer.UpdateCustomerResponse;
import com.project.fitstore.services.CustomerService;
import com.project.fitstore.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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
    private final ImageService imageService;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String fileName) {
        byte[] data = imageService.downloadImage(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @PostMapping("/customer/upload")
    public ResponseEntity<UpdateCustomerResponse> uploadCustomerImage(
            @RequestParam("image") MultipartFile imageFile, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();

        return new ResponseEntity<>(customerService.uploadCustomerImage(imageFile, customer.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{fileName}/delete")
    public ResponseEntity<String> deleteCustomerImage(@PathVariable String fileName, Authentication auth) {
        var customer = (Customer) auth.getPrincipal();
        return new ResponseEntity<>(customerService.deleteCustomerImage(fileName, customer.getId()), HttpStatus.OK);
    }
}
