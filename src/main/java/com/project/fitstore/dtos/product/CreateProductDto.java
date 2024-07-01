package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.SubCategory;

import java.math.BigDecimal;

public record CreateProductDto(String name, String brand, String sku, Category category, SubCategory subCategory, BigDecimal price){

}
