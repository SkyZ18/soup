package com.skyz.api.core.interfaceAdapters.dtos.mappers;

import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientWithoutAppResponse;
import com.skyz.api.core.models.SoupClient;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SoupClientResponseWithoutAppMapper implements Function<SoupClient, CreateClientWithoutAppResponse> {
    @Override
    public CreateClientWithoutAppResponse apply(SoupClient soupClient) {
        return new CreateClientWithoutAppResponse(
                soupClient.getClientId(),
                soupClient.getClientSecret()
        );
    }
}
