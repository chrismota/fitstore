package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductResponse(UUID id, String name, String brand, String sku, Category category, SubCategory subCategory, BigDecimal price) {
    public static UpdateProductResponse from(Product product){
        return new UpdateProductResponse(product.getId(), product.getName(), product.getBrand(), product.getSku(),
                product.getCategory(), product.getSubCategory(), product.getPrice());
    }
}
