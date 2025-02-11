package com.project.fitstore.exceptions.image;

import com.amazonaws.AmazonServiceException;

public class ImageDeleteException extends AmazonServiceException {
    public ImageDeleteException() {

        super("There was an error on while deleting the image.");
    }

    public ImageDeleteException(String message) {
        super(message);
    }
}
