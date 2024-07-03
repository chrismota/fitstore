package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateProductDto(String name, String brand, Category category, SubCategory subCategory, BigDecimal price){
}
