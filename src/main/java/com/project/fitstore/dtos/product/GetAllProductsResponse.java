package com.project.fitstore.dtos.product;

import com.project.fitstore.domain.product.Product;

import java.util.List;

public record GetAllProductsResponse(List<GetProductResponse> products) {
    public static GetAllProductsResponse from(List<Product> productList){
        return new GetAllProductsResponse(productList.stream().map(GetProductResponse::from).toList());
    }
}
