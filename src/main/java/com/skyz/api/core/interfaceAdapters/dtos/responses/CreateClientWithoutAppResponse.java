package com.skyz.api.core.interfaceAdapters.dtos.responses;

import lombok.Builder;

@Builder
public record CreateClientWithoutAppResponse(
        String clientId,
        String clientSecret
) {}
