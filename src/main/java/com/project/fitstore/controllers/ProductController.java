package com.project.fitstore.controllers;

import com.project.fitstore.dtos.product.GetAllProductsResponse;
import com.project.fitstore.dtos.product.GetProductResponse;
import com.project.fitstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category")
    public ResponseEntity<GetAllProductsResponse> getProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/subcategory")
    public ResponseEntity<GetAllProductsResponse> getProductsBySubCategory(@RequestParam String subcategory) {
        return ResponseEntity.ok(productService.getProductsBySubCategory(subcategory));
    }

    @GetMapping("/search")
    public ResponseEntity<GetAllProductsResponse> getProductsBySearchName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductsBySearchName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

}
