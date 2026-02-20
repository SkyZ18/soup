package com.skyz.api.core.config.exceptions;

public class MandatorNotFoundException extends RuntimeException {
    public MandatorNotFoundException(String mandator) {
        super("Country not found in System: " + mandator);
    }
}
