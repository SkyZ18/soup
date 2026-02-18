package com.skyz.api.core.interfaceAdapters.dtos.mappers;

import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import com.skyz.api.core.models.SoupClient;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SoupClientResponseMapper implements Function<SoupClient, CreateClientResponse> {
    @Override
    public CreateClientResponse apply(SoupClient soupClient) {
        return new CreateClientResponse(
                soupClient.getClientId(),
                soupClient.getClientSecret(),
                soupClient.getApplicationMeta().getUri()
        );
    }
}
