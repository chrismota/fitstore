package com.project.fitstore.controllers;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.project.fitstore.dtos.product.UpdateProductResponse;
import com.project.fitstore.services.ImageService;
import com.project.fitstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/image")
public class AdminImageController {

    private final ImageService imageService;
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<S3ObjectSummary>> getAllFiles() {
        return new ResponseEntity<>(imageService.listImages(), HttpStatus.OK);
    }

    @PostMapping("/product/{id}/upload")
    public ResponseEntity<UpdateProductResponse> uploadProductImage(@RequestParam("image") MultipartFile imageFile,
                                                                    @PathVariable("id") Long productId) {
        return new ResponseEntity<>(productService.uploadProductImage(imageFile, productId), HttpStatus.OK);
    }

    @DeleteMapping("/product/{fileName}/delete")
    public ResponseEntity<String> deleteProductImage(@PathVariable String fileName) {
        return new ResponseEntity<>(productService.deleteProductImage(fileName), HttpStatus.OK);
    }
}
