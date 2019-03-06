package com.bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TransactionResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FieldValidatorException.class)
    public final ResponseEntity<Object> handleFieldValidatorException(FieldValidatorException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getHttpStatus());
    }
}
