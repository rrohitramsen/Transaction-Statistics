package com.n26.exception;

/**
 * @author rohitkumar
 */
public class FutureTransactionException extends RuntimeException {

    public FutureTransactionException(String message) {
        super(message);
    }
}
