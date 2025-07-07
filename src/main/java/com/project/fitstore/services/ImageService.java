package com.project.fitstore.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.project.fitstore.exceptions.image.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${application.bucket.name}")
    private String bucketName;
    final AmazonS3 s3Client;

    public String uploadImage(MultipartFile imageFile) {
        File imageObj = convertMultiPartFileToFile(imageFile);
        String imageName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, imageName, imageObj));
        } catch (AmazonServiceException e) {
            throw new ImageUploadException();
        }

        imageObj.delete();
        return imageName;
    }

    public String updateImage(MultipartFile imageFile, String oldImage) {
        if (oldImage != null)
            deleteOldImage(oldImage);
        return uploadImage(imageFile);
    }

    public List<S3ObjectSummary> listImages() {
        List<S3ObjectSummary> summaries = new ArrayList<>();
        ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result result;

        do {
            result = s3Client.listObjectsV2(request);
            summaries.addAll(result.getObjectSummaries());
            request.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return summaries;
    }

    public void deleteImage(String fileName) {
        try {
            s3Client.getObject(bucketName, fileName);
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException e) {
            throw new ImageDeleteException();
        }
    }

    private void deleteOldImage(String oldImage) {
        try {
            s3Client.deleteObject(bucketName, oldImage);
        } catch (AmazonServiceException e) {
            throw new ImageDeleteException();
        }
    }

    public byte[] downloadImage(String fileName) {
        try {
            S3Object s3Object = s3Client.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new ImageDownloadException();
        } catch (AmazonServiceException e) {
            throw new ImageDownloadException("Image not found");
        }
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new ImageConvertionException();
        }
        return convertedFile;
    }
}
