package com.skyz.api.core.interfaceAdapters.dtos.responses;

import com.skyz.api.core.models.enums.AppTypes;
import lombok.Builder;

@Builder
public record CreateApplicationResponse(
        String name,
        String uri,
        AppTypes type
) {}
