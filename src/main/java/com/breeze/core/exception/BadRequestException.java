package com.breeze.core.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SampleException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, 40001, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, 40001, message);
    }

    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, code, message);
    }
}