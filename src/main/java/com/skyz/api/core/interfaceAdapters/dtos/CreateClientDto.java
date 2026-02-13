package com.skyz.api.core.interfaceAdapters.dtos;

import com.skyz.api.core.models.enums.Scope;

public record CreateClientDto(
        String applicationName,
        String registerUri,
        Scope scope
) {}
