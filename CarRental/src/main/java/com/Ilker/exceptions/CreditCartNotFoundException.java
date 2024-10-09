package com.Ilker.exceptions;

public class CreditCartNotFoundException extends RuntimeException {
    public CreditCartNotFoundException(String message) {
        super(message);
    }
}
