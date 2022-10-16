package com.example.product.app.exception.controller;

import com.example.product.app.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionControllerAdvice.class);

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        LOGGER.error("Business Exception Occurred : {} with Error Status {}", exception.getMessage(), exception.getErrorStatusCode());
        return ResponseEntity
                .status(exception.getErrorStatusCode())
                .body(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleGenericException(Exception exception) {
        LOGGER.error("Exception Occurred : {}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
