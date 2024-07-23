package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;

public record UpdateProductRequest(String name, String brand, Category category, SubCategory subCategory, BigDecimal price){
}
