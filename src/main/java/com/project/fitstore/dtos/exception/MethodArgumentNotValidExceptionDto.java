package com.project.fitstore.dtos.exception;

public record MethodArgumentNotValidExceptionDto(String field, String errorMessage) {
}