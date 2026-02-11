package com.dtt.simulations.consentResponses;



public class ConsentNotFoundException extends RuntimeException {

    public ConsentNotFoundException(String message) {
        super(message);
    }

    public ConsentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
