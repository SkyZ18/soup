package com.skyz.api.core.config.exceptions;

public class ClientAlreadyRegisteredException extends RuntimeException {
    public ClientAlreadyRegisteredException() {
        super("This application already has a registered application");
    }
}
