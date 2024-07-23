package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateProductRequest(String name, String brand, String sku, Category category, SubCategory subCategory, BigDecimal price){
    public Product toProduct(){
        return new Product(null, name, brand, sku, category, subCategory, price, LocalDateTime.now(), LocalDateTime.now());
    }
}
