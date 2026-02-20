package com.skyz.api.core.interfaceAdapters.dtos;

public record BindAppToClientDto(
        String clientId,
        String clientSecret,
        String appUri
) {}
