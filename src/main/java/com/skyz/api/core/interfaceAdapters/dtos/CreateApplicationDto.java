package com.skyz.api.core.interfaceAdapters.dtos;

import com.skyz.api.core.models.enums.AppTypes;

public record CreateApplicationDto(
        String name,
        String uri,
        AppTypes type
) {}
