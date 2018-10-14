package com.n26.api;

import com.n26.exception.FutureTransactionException;
import com.n26.exception.OlderTransactionException;
import com.n26.exception.StatisticsNotAvailableException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TransactionResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new Response(HttpStatus.BAD_REQUEST.value(), StringUtils.EMPTY));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return buildResponseEntity(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), StringUtils.EMPTY));
    }

    private ResponseEntity<Object> buildResponseEntity(Response response) {
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @ExceptionHandler(FutureTransactionException.class)
    public final ResponseEntity<Object> handleFutureTransactionException(FutureTransactionException ex, WebRequest request) {
        return buildResponseEntity(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), StringUtils.EMPTY));
    }

    @ExceptionHandler(OlderTransactionException.class)
    public final ResponseEntity<Object> handleOlderTransactionException(OlderTransactionException ex, WebRequest request) {
        return buildResponseEntity(new Response(HttpStatus.NO_CONTENT.value(), StringUtils.EMPTY));
    }

    @ExceptionHandler(StatisticsNotAvailableException.class)
    public final ResponseEntity<Object> handleStatisticsNotAvailableException(StatisticsNotAvailableException ex, WebRequest request) {
        return buildResponseEntity(new Response(HttpStatus.BAD_REQUEST.value(), StringUtils.EMPTY));
    }
}
