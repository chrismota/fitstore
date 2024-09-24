package com.project.fitstore.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${application.bucket.name}")
    private String bucketName;
    final AmazonS3 s3Client;
    final ProductService productService;
    final CustomerService customerService;

    public String uploadProductImage(MultipartFile file, UUID id) {
        var product = productService.findProductById(id);

        String imageName = uploadImage(file);

        product.setImagePath(imageName);
        product.setUpdatedAt(LocalDateTime.now());
        productService.saveProduct(product);

        return "Image uploaded successfully: " + imageName;
    }

    public String uploadCustomerImage(MultipartFile imageFile, UUID id) {
        var customer = customerService.findCustomerById(id);

        String imageName = uploadImage(imageFile);

        customer.setImagePath(imageName);
        customer.setUpdatedAt(LocalDateTime.now());
        customerService.saveCustomer(customer);

        return "Image uploaded successfully: " + imageName;
    }

    public String updateProductImage(MultipartFile imageFile, UUID id) {
        var product = productService.findProductById(id);
        String oldImage = product.getImagePath();

        var newImage = uploadImage(imageFile);

        if(oldImage != null)
            deleteOldImage(oldImage);

        product.setImagePath(newImage);
        product.setUpdatedAt(LocalDateTime.now());
        productService.saveProduct(product);

        return newImage + " added.";
    }

    public String updateCustomerImage(MultipartFile imageFile, UUID id) {
        var customer = customerService.findCustomerById(id);
        String oldImage = customer.getImagePath();

        var newImage = uploadImage(imageFile);

        if(oldImage != null)
            deleteOldImage(oldImage);

        customer.setImagePath(newImage);
        customer.setUpdatedAt(LocalDateTime.now());
        customerService.saveCustomer(customer);

        return newImage + " added.";
    }

    public List<S3ObjectSummary> listObjects() {
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    public String deleteImage(String fileName) {
        try {
            s3Client.getObject(bucketName, fileName);
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException("There was an error on the delete attempt of the image.");
        }

        var product = productService.findProductByImage(fileName);

        if(product != null) {
            product.setImagePath(null);
            product.setUpdatedAt(LocalDateTime.now());
            productService.saveProduct(product);
        }

        var customer = customerService.findCustomerByImage(fileName);

        if(customer != null) {
            customer.setImagePath(null);
            customer.setUpdatedAt(LocalDateTime.now());
            customerService.saveCustomer(customer);
        }

        return fileName + " successfully deleted.";
    }

    public String deleteCustomerImage(String fileName) {
        try {
            s3Client.getObject(bucketName, fileName);
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException("There was an error on the delete attempt of the image.");
        }

        var customer = customerService.findCustomerByImage(fileName);

        if(customer != null) {
            customer.setImagePath(null);
            customer.setUpdatedAt(LocalDateTime.now());
            customerService.saveCustomer(customer);
        }

        return fileName + " successfully deleted.";
    }

    private String uploadImage(MultipartFile imageFile) {
        File imageObj = convertMultiPartFileToFile(imageFile);
        String imageName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, imageName, imageObj));
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException(e.getMessage());
        }

        imageObj.delete();
        return imageName;
    }

    private void deleteOldImage(String oldImage) {
        try {
            s3Client.deleteObject(bucketName, oldImage);
        } catch (AmazonServiceException e) {
            throw new AmazonServiceException(e.getMessage());
        }
    }

    public byte[] downloadImage(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return convertedFile;
    }
}
