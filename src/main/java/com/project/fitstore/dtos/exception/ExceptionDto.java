package com.project.fitstore.dtos.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(String message, HttpStatus status) {
}
