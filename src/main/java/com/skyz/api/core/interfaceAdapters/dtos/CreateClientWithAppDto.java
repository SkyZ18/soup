package com.skyz.api.core.interfaceAdapters.dtos;

import com.skyz.api.core.models.enums.Scope;

public record CreateClientWithAppDto(
        String applicationName,
        String registerUri,
        String mandator,
        Scope scope
) {}
