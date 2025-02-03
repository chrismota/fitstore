package com.project.fitstore.exceptions.image;

import com.amazonaws.AmazonServiceException;

public class ImageUploadException extends AmazonServiceException {
    public ImageUploadException() {
        super("There was an error on while uploading the image.");
    }
    public ImageUploadException(String message) {
        super(message);
    }
}
