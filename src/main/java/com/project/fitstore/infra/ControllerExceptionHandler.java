package com.project.fitstore.infra;

import com.project.fitstore.dtos.exception.ExceptionDto;
import com.project.fitstore.dtos.exception.MethodArgumentNotValidExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(RuntimeException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<MethodArgumentNotValidExceptionDto> threatMethodArgumentNotValidException
            (MethodArgumentNotValidException expection) {

        List<MethodArgumentNotValidExceptionDto> errorList = new ArrayList<>();

        List<FieldError> fieldErrors = expection.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            MethodArgumentNotValidExceptionDto error = new MethodArgumentNotValidExceptionDto(fieldError.getField(), message);
            errorList.add(error);
        }
        return errorList;

    }
}
