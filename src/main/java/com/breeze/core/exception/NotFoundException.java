package com.breeze.core.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends SampleException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, 40400, HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, 40400, message);
    }

    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}
