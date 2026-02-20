package com.skyz.api.core.config.exceptions;

public class ApplicationAlreadyExistsException extends RuntimeException {
    public ApplicationAlreadyExistsException(String uri) {
        super("This application already exists. Please use existing app or use another uri: " + uri);
    }
}
