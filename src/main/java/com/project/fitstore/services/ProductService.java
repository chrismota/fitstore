package com.project.fitstore.services;

import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.product.CreateProductDto;
import com.project.fitstore.dtos.product.UpdateProductDto;
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(CreateProductDto createProductDto) {
        return productRepository.save(createProductDto.toProduct());
    }

    public Product updateProduct(UUID id, UpdateProductDto updateProductDto) {
        Product product = this.findProductById(id);

        product.setName(updateProductDto.name());
        product.setBrand(updateProductDto.brand());
        product.setCategory(updateProductDto.category());
        product.setSubCategory(updateProductDto.subCategory());
        product.setPrice(updateProductDto.price());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    public String deleteProduct(UUID id) {
        productRepository.delete(this.findProductById(id));
        return "Product deleted successfully.";
    }

    public Product getProduct(UUID id) {
        return this.findProductById(id);
    }

    public Product findProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("Product not found");
    }
}
