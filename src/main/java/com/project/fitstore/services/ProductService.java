package com.project.fitstore.services;

import com.project.fitstore.domain.product.Product;
import com.project.fitstore.dtos.order.CreateItemRequest;
import com.project.fitstore.dtos.order.CreateOrderRequest;
import com.project.fitstore.dtos.product.*;
import com.project.fitstore.exceptions.product.ProductImageNotFoundException;
import com.project.fitstore.exceptions.product.ProductNotFoundException;
import com.project.fitstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final ImageService imageService;

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
        product.setDescription(updateProductRequest.description());
        product.setBrand(updateProductRequest.brand());
        product.setCategory(updateProductRequest.category());
        product.setSubCategory(updateProductRequest.subCategory());
        product.setPrice(updateProductRequest.price());
        product.setUpdatedAt(LocalDateTime.now());

        return UpdateProductResponse.from(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(UUID id) {
        var product = findProductById(id);
        if (product.getImagePath() != null) {
            imageService.deleteImage(product.getImagePath());
        }
        productRepository.delete(product);
    }

    public String uploadProductImage(MultipartFile file, UUID id) {
        var product = findProductById(id);
        String imageName = imageService.uploadImage(file);

        product.setImagePath(imageName);
        product.setUpdatedAt(LocalDateTime.now());
        saveProduct(product);
        return "Image uploaded successfully: " + imageName;
    }

    public String updateProductImage(MultipartFile imageFile, UUID id) {
        var product = findProductById(id);
        String oldImage = product.getImagePath();

        String newImage = imageService.updateImage(imageFile, oldImage);

        product.setImagePath(newImage);
        product.setUpdatedAt(LocalDateTime.now());
        saveProduct(product);

        return newImage + " added.";
    }

    public String deleteProductImage(String fileName) {
        var product = findProductByImage(fileName);

        if (product == null) {
            throw new ProductImageNotFoundException("Image does not belong to any product or does not exist.");
        }

        imageService.deleteImage(fileName);
        product.setImagePath(null);
        product.setUpdatedAt(LocalDateTime.now());
        saveProduct(product);

        return fileName + " successfully deleted.";
    }

    public Product findProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException();
    }

    public Product findProductByImage(String image) {
        Optional<Product> product = productRepository.findProductByImagePath(image);
        return product.orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }


    public void checkIfProductsExists(CreateOrderRequest createOrderRequest) {
        List<UUID> productIds = createOrderRequest.products().stream().map(CreateItemRequest::id).toList();
        var products = productRepository.findByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new ProductNotFoundException("One or more products were not found.");
        }
    }
}
