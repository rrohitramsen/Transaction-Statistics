package com.bank.exception;

import org.springframework.http.HttpStatus;

/**
 * @author rohitkumar
 */
public class FieldValidatorException extends RuntimeException {

    private HttpStatus httpStatus;

    public FieldValidatorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
