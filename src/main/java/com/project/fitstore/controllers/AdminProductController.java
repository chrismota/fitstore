package com.project.fitstore.controllers;

import com.project.fitstore.dtos.product.*;
import com.project.fitstore.repositories.ProductRepository;
import com.project.fitstore.services.ProductService;
import com.project.fitstore.services.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminProductController {
    final ProductService productService;
    final ProductRepository productRepository;
    final ImageService imageService;


    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable("id") UUID id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, updateProductRequest));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
