package com.project.fitstore.infra;

import com.project.fitstore.dtos.exception.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(RuntimeException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
