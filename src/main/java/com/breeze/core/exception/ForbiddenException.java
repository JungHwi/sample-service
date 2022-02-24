package com.breeze.core.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends SampleException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, 40300, "Forbidden.");
    }

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, 40300, message);
    }
}
