package com.skyz.api.core.config.exceptions;

public class ApplicationNotRegisteredException extends RuntimeException {
    public ApplicationNotRegisteredException(String uri) {
        super("Application is not registered on this idp: " + uri + ". Please register new application.");
    }
}
