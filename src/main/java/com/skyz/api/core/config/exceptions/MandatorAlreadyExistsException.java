package com.skyz.api.core.config.exceptions;

public class MandatorAlreadyExistsException extends RuntimeException {
    public MandatorAlreadyExistsException(String countryCode) {
        super("Mandator already in System: " + countryCode);
    }
}
