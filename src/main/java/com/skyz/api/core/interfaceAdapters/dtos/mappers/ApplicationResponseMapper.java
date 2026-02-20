package com.skyz.api.core.interfaceAdapters.dtos.mappers;

import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateApplicationResponse;
import com.skyz.api.core.models.ApplicationMeta;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ApplicationResponseMapper implements Function<ApplicationMeta, CreateApplicationResponse> {
    @Override
    public CreateApplicationResponse apply(ApplicationMeta applicationMeta) {
        return new CreateApplicationResponse(
                applicationMeta.getName(),
                applicationMeta.getUri(),
                applicationMeta.getType()
        );
    }
}
