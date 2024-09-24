package com.project.fitstore.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitstore.dtos.product.GetAllProductsResponse;
import com.project.fitstore.dtos.product.GetProductResponse;
import com.project.fitstore.services.ProductService;

import lombok.RequiredArgsConstructor;

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
