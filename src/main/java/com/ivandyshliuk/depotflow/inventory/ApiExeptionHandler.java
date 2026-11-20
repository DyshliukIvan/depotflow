package com.ivandyshliuk.depotflow.inventory;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 import org.hibernate.exception.ConstraintViolationException;


@RestControllerAdvice
public class ApiExeptionHandler {
    @ExceptionHandler(DublicateResourseExeption.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicateResourse(DublicateResourseExeption exeption) {
        return new ApiError(exeption.getMessage());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleResourceNotFound(ResourceNotFoundException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException exception) {
        Throwable cause = exception.getCause();
        while (cause != null) {
            if (cause instanceof org.hibernate.exception.ConstraintViolationException violation) {
                String constraintName = violation.getConstraintName();
                if ("products_sku_key".equals(constraintName)) return new ResponseEntity<>(new ApiError("Product with this SKU already exists"), HttpStatus.CONFLICT);
            }
            cause = cause.getCause();
        }
        return new ResponseEntity<>(new ApiError("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
