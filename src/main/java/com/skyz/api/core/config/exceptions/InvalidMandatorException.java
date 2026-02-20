package com.skyz.api.core.config.exceptions;

public class InvalidMandatorException extends RuntimeException {
    public InvalidMandatorException() {
        super("The given Mandator is not valid. Nothing created");
    }
}
