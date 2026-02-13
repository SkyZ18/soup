package com.skyz.api.core.interfaceAdapters.dtos.responses;

import lombok.Builder;

@Builder
public record CreateClientResponse(
        String clientId,
        String clientSecret,
        String registeredUri
) {
}
