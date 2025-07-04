package com.project.fitstore.services;

import com.project.fitstore.domain.product.Category;
import com.project.fitstore.domain.product.Product;
import com.project.fitstore.domain.product.SubCategory;
import com.project.fitstore.dtos.order.CreateItemRequest;
import com.project.fitstore.dtos.order.CreateOrderRequest;
import com.project.fitstore.dtos.product.*;
import com.project.fitstore.exceptions.product.*;
import com.project.fitstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final ImageService imageService;

    public GetAllProductsResponse getAllProducts() {
        return GetAllProductsResponse.from(productRepository.findAll());
    }

    public GetAllProductsResponse getProductsByCategory(String categoryParam) {
        Category category;
        try {
            category = Category.valueOf(categoryParam.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ProductCategoryNotFoundException("Invalid parameter value for category: " + categoryParam);
        }
        return GetAllProductsResponse.from(productRepository.findProductsByCategory(category));
    }

    public GetAllProductsResponse getProductsBySubCategory(String subCategoryParam) {
        SubCategory subCategory;
        try {
            subCategory = SubCategory.valueOf(subCategoryParam.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ProductSubCategoryNotFoundException("Invalid parameter value for subcategory: " + subCategoryParam);
        }

        return GetAllProductsResponse.from(productRepository.findProductsBySubCategory(subCategory));
    }

    public GetAllProductsResponse getProductsBySearchName(String name) {
        return GetAllProductsResponse.from(productRepository.findProductsByNameContainingIgnoreCase(name));
    }

    public GetProductResponse getProduct(Long id) {
        return GetProductResponse.from(findProductById(id));
    }

    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product;
        try {
            product = productRepository.save(createProductRequest.toProduct());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateProductSkuException("Product with sku " + createProductRequest.sku() + " already exists.");
        }
        return CreateProductResponse.from(product);
    }

    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
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
    public void deleteProduct(Long id) {
        var product = findProductById(id);
        if (product.getImagePath() != null) {
            imageService.deleteImage(product.getImagePath());
        }
        productRepository.delete(product);
    }

    public UpdateProductResponse uploadProductImage(MultipartFile imageFile, Long id) {
        var product = findProductById(id);
        String oldImage = product.getImagePath();

        String newImage = imageService.updateImage(imageFile, oldImage);

        product.setImagePath(newImage);
        product.setUpdatedAt(LocalDateTime.now());

        return UpdateProductResponse.from(productRepository.save(product));

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

    public Product findProductById(Long id) {
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
        List<Long> productIds = createOrderRequest.products().stream().map(CreateItemRequest::id).toList();
        var products = productRepository.findByIdIn(productIds);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found for the provided IDs.");
        }

        if (products.size() != productIds.size()) {
            throw new ProductNotFoundException("One or more products were not found.");
        }
    }
}
