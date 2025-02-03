package com.project.fitstore.exceptions.image;

public class ImageDownloadException extends RuntimeException {
    public ImageDownloadException() {
        super("There was an error on while downloading the image.");
    }
    public ImageDownloadException(String message) {
        super(message);
    }
}
