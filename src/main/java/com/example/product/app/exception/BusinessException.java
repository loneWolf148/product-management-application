package com.example.product.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus errorStatusCode;

    public BusinessException(String message, HttpStatus errorStatusCode) {
        super(message);
        this.errorStatusCode = errorStatusCode;
    }

    public BusinessException() {
        this.errorStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}