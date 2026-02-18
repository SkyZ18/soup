package com.skyz.api.core.interfaceAdapters.dtos;

public record ClientWithIdAndPasswordDto(
        String clientId,
        String clientSecret
) {
}
