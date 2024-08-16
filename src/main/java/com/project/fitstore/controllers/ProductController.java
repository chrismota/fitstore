package com.project.fitstore.controllers;

import com.project.fitstore.dtos.product.*;
import com.project.fitstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

}
