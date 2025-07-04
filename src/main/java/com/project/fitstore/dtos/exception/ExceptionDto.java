package com.project.fitstore.dtos.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(
        String message, HttpStatus status,
        String errorCode, LocalDateTime timestamp) {
    public ExceptionDto(String message, HttpStatus status, String errorCode) {
        this(message, status, errorCode, LocalDateTime.now());
    }
}
