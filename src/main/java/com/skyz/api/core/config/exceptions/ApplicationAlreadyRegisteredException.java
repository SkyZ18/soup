package com.skyz.api.core.config.exceptions;

public class ApplicationAlreadyRegisteredException extends RuntimeException {
    public ApplicationAlreadyRegisteredException(String uri) {
        super("This uri is already registered for this idp: " + uri);
    }
}
