package com.n26.exception;

/**
 * @author rohitkumar
 */
public class OlderTransactionException extends RuntimeException {

    public OlderTransactionException(String message) {
        super(message);
    }
}
