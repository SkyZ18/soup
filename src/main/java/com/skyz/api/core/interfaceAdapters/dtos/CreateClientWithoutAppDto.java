package com.skyz.api.core.interfaceAdapters.dtos;

import com.skyz.api.core.models.enums.Scope;

public record CreateClientWithoutAppDto(
        Scope scope,
        String mandator
) {}
