package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateProductRequest(
        @NotNull(message = "name is mandatory")
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotNull(message = "brand is mandatory")
        @NotBlank(message = "brand cannot be blank")
        String brand,
        @NotNull(message = "sku is mandatory")
        @NotBlank(message = "sku cannot be blank")
        String sku,
        Category category,
        SubCategory subCategory,
        @NotNull(message = "price is mandatory")
        BigDecimal price){
    public Product toProduct(){
        return new Product(null, name, brand, sku, category, subCategory, price, null, LocalDateTime.now(), LocalDateTime.now());
    }
}
