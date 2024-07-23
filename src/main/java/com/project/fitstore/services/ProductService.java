package com.project.fitstore.services;

import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.product.*;
import com.project.fitstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;

    public GetAllProductsResponse getAllProducts() {
        return GetAllProductsResponse.from(productRepository.findAll());
    }

    public GetProductResponse getProduct(UUID id) {
        return GetProductResponse.from(findProductById(id));
    }

    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        return CreateProductResponse.from(productRepository.save(createProductRequest.toProduct()));
    }

    public UpdateProductResponse updateProduct(UUID id, UpdateProductRequest updateProductRequest) {
        Product product = findProductById(id);

        product.setName(updateProductRequest.name());
        product.setBrand(updateProductRequest.brand());
        product.setCategory(updateProductRequest.category());
        product.setSubCategory(updateProductRequest.subCategory());
        product.setPrice(updateProductRequest.price());
        product.setUpdatedAt(LocalDateTime.now());

        return UpdateProductResponse.from(productRepository.save(product));
    }

    public String deleteProduct(UUID id) {
        productRepository.delete(findProductById(id));
        return "Product deleted successfully.";
    }

    public Product findProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("Product not found");
    }
}
