package com.project.fitstore.controllers;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.project.fitstore.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/image")
public class AdminImageController {

    private final ImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<List<S3ObjectSummary>> getAllFiles() {
        return new ResponseEntity<>(imageService.listObjects(), HttpStatus.OK);
    }

    @PostMapping("/product/{id}/upload")
    public ResponseEntity<String> uploadProductImage(@RequestParam("image") MultipartFile imageFile, @PathVariable("id") UUID productId) {
        return new ResponseEntity<>(imageService.uploadProductImage(imageFile, productId), HttpStatus.OK);
    }

    @PutMapping("/product/{id}/update")
    public ResponseEntity<String> updateProductImage(@RequestParam("image") MultipartFile imageFile, @PathVariable("id") UUID productId) {
        return new ResponseEntity<>(imageService.updateProductImage(imageFile, productId),HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String fileName) {
        byte[] data = imageService.downloadImage(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteProductImage(@PathVariable String fileName) {
        return new ResponseEntity<>(imageService.deleteImage(fileName),HttpStatus.OK);
    }
}
