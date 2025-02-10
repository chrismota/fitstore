package com.project.fitstore.infra;

import com.project.fitstore.dtos.exception.ExceptionDto;
import com.project.fitstore.dtos.exception.MethodArgumentNotValidExceptionDto;
import com.project.fitstore.exceptions.coupon.CouponExpiredException;
import com.project.fitstore.exceptions.coupon.CouponNotAttendsMinValueException;
import com.project.fitstore.exceptions.coupon.CouponNotFoundException;
import com.project.fitstore.exceptions.coupon.CouponUnexpectedPercentageException;
import com.project.fitstore.exceptions.customer.CustomerImageNotFoundException;
import com.project.fitstore.exceptions.customer.CustomerNotFoundException;
import com.project.fitstore.exceptions.image.ImageConvertionException;
import com.project.fitstore.exceptions.image.ImageDeleteException;
import com.project.fitstore.exceptions.image.ImageDownloadException;
import com.project.fitstore.exceptions.image.ImageUploadException;
import com.project.fitstore.exceptions.order.OrderExpiredException;
import com.project.fitstore.exceptions.order.OrderNotFoundException;
import com.project.fitstore.exceptions.order.OrderNotValidException;
import com.project.fitstore.exceptions.payment.PaymentAttemptFailedException;
import com.project.fitstore.exceptions.payment.PaymentNotFoundException;
import com.project.fitstore.exceptions.product.ProductImageNotFoundException;
import com.project.fitstore.exceptions.product.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
    public ResponseEntity<ExceptionDto> threatGeneralException(Exception exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatProductNotFoundException(ProductNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(ProductImageNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatProductImageNotFoundException(ProductImageNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatPaymentNotFoundException(PaymentNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(PaymentAttemptFailedException.class)
    public ResponseEntity<ExceptionDto> threatPaymentAttemptFailedException(PaymentAttemptFailedException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exceptionDTO);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatOrderNotFoundException(OrderNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(OrderNotValidException.class)
    public ResponseEntity<ExceptionDto> threatOrderNotValidException(OrderNotValidException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(OrderExpiredException.class)
    public ResponseEntity<ExceptionDto> threatOrderExpiredException(OrderExpiredException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatCustomerNotFoundException(CustomerNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(CustomerImageNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatCustomerImageNotFoundException(CustomerImageNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ExceptionDto> threatCouponNotFoundException(CouponNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(CouponExpiredException.class)
    public ResponseEntity<ExceptionDto> threatCouponExpiredException(CouponExpiredException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CouponNotAttendsMinValueException.class)
    public ResponseEntity<ExceptionDto> threatCouponNotAttendsMinValueException(CouponNotAttendsMinValueException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CouponUnexpectedPercentageException.class)
    public ResponseEntity<ExceptionDto> threatCouponUnexpectedPercentageException(CouponUnexpectedPercentageException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ExceptionDto> threatImageUploadException(ImageUploadException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageDeleteException.class)
    public ResponseEntity<ExceptionDto> threatImageDeleteException(ImageDeleteException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageDownloadException.class)
    public ResponseEntity<ExceptionDto> threatImageDownloadException(ImageDownloadException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageConvertionException.class)
    public ResponseEntity<ExceptionDto> threatImageConvertionException(ImageConvertionException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

}
