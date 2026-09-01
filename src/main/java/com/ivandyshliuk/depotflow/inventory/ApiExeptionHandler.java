package com.ivandyshliuk.depotflow.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExeptionHandler {
    @ExceptionHandler(DublicateResourseExeption.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicateResourse(DublicateResourseExeption exeption) {
        return new ApiError(exeption.getMessage());
    }
}
