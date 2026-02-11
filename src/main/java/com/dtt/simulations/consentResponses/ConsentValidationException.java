package com.dtt.simulations.consentResponses;


public class ConsentValidationException extends RuntimeException {

    public ConsentValidationException(String message) {
        super(message);
    }

    public ConsentValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

