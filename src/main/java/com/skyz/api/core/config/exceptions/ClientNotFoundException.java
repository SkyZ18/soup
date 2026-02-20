package com.skyz.api.core.config.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String id) {
        super("Client not found in System: " + id);
    }
}
