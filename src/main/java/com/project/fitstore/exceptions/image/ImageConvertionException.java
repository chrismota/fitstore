package com.project.fitstore.exceptions.image;

public class ImageConvertionException extends RuntimeException {
    public ImageConvertionException() {
        super("There was an error on while converting the image.");
    }
    public ImageConvertionException(String message) {
        super(message);
    }
}
