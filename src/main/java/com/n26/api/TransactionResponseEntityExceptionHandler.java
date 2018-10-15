package com.n26.api;

import com.n26.exception.FutureTransactionException;
import com.n26.exception.OlderTransactionException;
import com.n26.exception.StatisticsNotAvailableException;
import com.n26.exception.FieldsNotParsableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TransactionResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FutureTransactionException.class)
    public final ResponseEntity<Object> handleFutureTransactionException(FutureTransactionException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OlderTransactionException.class)
    public final ResponseEntity<Object> handleOlderTransactionException(OlderTransactionException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(StatisticsNotAvailableException.class)
    public final ResponseEntity<Object> handleStatisticsNotAvailableException(StatisticsNotAvailableException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldsNotParsableException.class)
    public final ResponseEntity<Object> handleTransactionDateNotParsableException(FieldsNotParsableException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
