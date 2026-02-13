package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.ClientRepository;
import com.skyz.api.core.config.ClientSecretGenerator;
import com.skyz.api.core.config.exceptions.ApplicationAlreadyRegisteredException;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientDto;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import com.skyz.api.core.models.SoupClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(
            final ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
    }

    public Optional<CreateClientResponse> createNewClientWithAppInfo(CreateClientDto createClient) {
        SoupClient soupClient = new SoupClient();
        var clients = clientRepository.findByUri(createClient.registerUri());

        if(clients != null) {
            throw new ApplicationAlreadyRegisteredException(createClient.registerUri());
        }

        soupClient.setClientId(generateClientId());
        soupClient.setClientSecret(ClientSecretGenerator.generate());
        soupClient.setUri(createClient.registerUri());
        soupClient.setRegistered(true);
        soupClient.setScope(createClient.scope());

        clientRepository.save(soupClient);

        return Optional.of(CreateClientResponse.builder()
                .clientId(soupClient.getClientId())
                .clientSecret(soupClient.getClientSecret())
                .registeredUri(soupClient.getUri())
                .build());
    }

    private String generateClientId() {
        var id = UUID.randomUUID().toString();

        while(clientRepository.existsByClientId(id)) {
            id = UUID.randomUUID().toString();
        }

        return id;
    }
}
