package com.project.fitstore.infra;

import com.project.fitstore.dtos.exception.*;
import com.project.fitstore.exceptions.ErrorCode;
import com.project.fitstore.exceptions.auth.*;
import com.project.fitstore.exceptions.coupon.*;
import com.project.fitstore.exceptions.customer.*;
import com.project.fitstore.exceptions.general.*;
import com.project.fitstore.exceptions.image.*;
import com.project.fitstore.exceptions.order.*;
import com.project.fitstore.exceptions.payment.*;
import com.project.fitstore.exceptions.product.*;
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
    public ResponseEntity<ExceptionDto> handleGeneralException(Exception exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR.name());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<MethodArgumentNotValidExceptionDto> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exception) {

        List<MethodArgumentNotValidExceptionDto> errorList = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            MethodArgumentNotValidExceptionDto error =
                    new MethodArgumentNotValidExceptionDto(fieldError.getField(), message);
            errorList.add(error);
        }
        return errorList;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionDto> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.INVALID_CREDENTIALS.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ExceptionDto> handleStatusNotFoundException(InvalidStatusException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.INVALID_STATUS_VALUE.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductCategoryNotFoundException(ProductCategoryNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.CATEGORY_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(ProductSubCategoryNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductSubCategoryNotFoundException(ProductSubCategoryNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.SUB_CATEGORY_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(ProductImageNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductImageNotFoundException(ProductImageNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(DuplicateProductSkuException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateProductSkuException(DuplicateProductSkuException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.CONFLICT, ErrorCode.DUPLICATE_FIELD.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlePaymentNotFoundException(PaymentNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(PaymentAttemptFailedException.class)
    public ResponseEntity<ExceptionDto> handlePaymentAttemptFailedException(PaymentAttemptFailedException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.INTERNAL_ERROR.name());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exceptionDTO);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleOrderNotFoundException(OrderNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ORDER_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(OrderNotValidException.class)
    public ResponseEntity<ExceptionDto> handleOrderNotValidException(OrderNotValidException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.INVALID_ORDER.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(OrderExpiredException.class)
    public ResponseEntity<ExceptionDto> handleOrderExpiredException(OrderExpiredException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.ORDER_EXPIRED.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(OrderHasPaymentRecordException.class)
    public ResponseEntity<ExceptionDto> handleOrderHasPaymentRecordException(OrderHasPaymentRecordException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.ORDER_HAS_PAYMENT_RECORD.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(DuplicateCustomerFieldException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateCustomerFieldException(DuplicateCustomerFieldException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.CONFLICT, ErrorCode.DUPLICATE_FIELD.name());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.CUSTOMER_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(IncorrectCurrentPassword.class)
    public ResponseEntity<ExceptionDto> handleIncorrectCurrentPasswordException
            (IncorrectCurrentPassword exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.INCORRECT_CURRENT_PASSWORD.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CustomerImageNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCustomerImageNotFoundException(CustomerImageNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCouponNotFoundException(CouponNotFoundException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.COUPON_NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(CouponExpiredException.class)
    public ResponseEntity<ExceptionDto> handleCouponExpiredException(CouponExpiredException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.COUPON_EXPIRED.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CouponNotAttendsMinValueException.class)
    public ResponseEntity<ExceptionDto> handleCouponNotAttendsMinValueException(CouponNotAttendsMinValueException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.COUPON_NOT_APPLICABLE.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(CouponUnexpectedPercentageException.class)
    public ResponseEntity<ExceptionDto> handleCouponUnexpectedPercentageException(CouponUnexpectedPercentageException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.COUPON_LIMIT_EXCEEDED.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(DuplicateCouponCodeException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateCouponCodeException(DuplicateCouponCodeException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.CONFLICT, ErrorCode.DUPLICATE_FIELD.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ExceptionDto> handleImageUploadException(ImageUploadException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.IMAGE_UPLOAD_ERROR.name());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageDeleteException.class)
    public ResponseEntity<ExceptionDto> handleImageDeleteException(ImageDeleteException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.IMAGE_DELETE_ERROR.name());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageDownloadException.class)
    public ResponseEntity<ExceptionDto> handleImageDownloadException(ImageDownloadException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR.name());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

    @ExceptionHandler(ImageConvertionException.class)
    public ResponseEntity<ExceptionDto> handleImageConvertionException(ImageConvertionException exception) {
        ExceptionDto exceptionDTO = new ExceptionDto(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR.name());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

}
