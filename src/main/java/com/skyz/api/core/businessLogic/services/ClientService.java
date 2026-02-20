package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.ApplicationRepository;
import com.skyz.api.core.businessLogic.repositories.ClientRepository;
import com.skyz.api.core.businessLogic.repositories.MandatorRepository;
import com.skyz.api.core.config.ClientIdGenerator;
import com.skyz.api.core.config.ClientSecretGenerator;
import com.skyz.api.core.config.exceptions.*;
import com.skyz.api.core.interfaceAdapters.dtos.BindAppToClientDto;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithoutAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.ClientWithIdAndPasswordDto;
import com.skyz.api.core.interfaceAdapters.dtos.mappers.SoupClientResponseMapper;
import com.skyz.api.core.interfaceAdapters.dtos.mappers.SoupClientResponseWithoutAppMapper;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientWithoutAppResponse;
import com.skyz.api.core.models.ApplicationMeta;
import com.skyz.api.core.models.SoupClient;
import com.skyz.api.core.models.Mandator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final ApplicationRepository applicationRepository;
    private final MandatorRepository mandatorRepository;
    private final SoupClientResponseMapper soupClientResponseMapper;
    private final SoupClientResponseWithoutAppMapper soupClientResponseWithoutAppMapper;
    private final ClientIdGenerator clientIdGenerator;

    public ClientService(
            final ClientRepository clientRepository,
            final ApplicationRepository applicationRepository,
            final MandatorRepository mandatorRepository,
            final SoupClientResponseMapper soupClientResponseMapper,
            final SoupClientResponseWithoutAppMapper soupClientResponseWithoutAppMapper,
            final ClientIdGenerator clientIdGenerator
    ) {
        this.clientRepository = clientRepository;
        this.applicationRepository = applicationRepository;
        this.mandatorRepository = mandatorRepository;
        this.soupClientResponseMapper = soupClientResponseMapper;
        this.soupClientResponseWithoutAppMapper = soupClientResponseWithoutAppMapper;
        this.clientIdGenerator = clientIdGenerator;
    }

    public Optional<List<SoupClient>> returnAllClientsWithSecret() {
        return Optional.of(clientRepository.findAll());
    }

    public Optional<List<SoupClient>> returnAllClientsWithSecretByRegistered(boolean registered) {
        return Optional.of(clientRepository.findByRegistered(registered));
    }

    @Transactional
    public Optional<CreateClientResponse> createNewClientWithAppInfo(CreateClientWithAppDto dto) {
        log.info("Starting client creation Process");
        ApplicationMeta application = applicationRepository.findByUri(dto.registerUri())
                .orElseThrow(() -> new ApplicationNotRegisteredException(dto.registerUri()));

        if (clientRepository.existsByApplicationMeta_Uri(application.getUri())) {
            throw new ApplicationAlreadyRegisteredException(application.getUri());
        }

        Mandator mandator = mandatorRepository.findByCountryCode(dto.mandator())
                .orElseThrow(() -> new MandatorNotFoundException(dto.mandator()));

        SoupClient createdClient = SoupClient.builder()
                .clientId(clientIdGenerator.generate())
                .clientSecret(ClientSecretGenerator.generate())
                .applicationMeta(application)
                .registered(true)
                .scope(dto.scope())
                .mandator(mandator)
                .build();

        clientRepository.save(createdClient);
        log.info("Client created: {}", createdClient.getClientId());

        return Optional.of(createdClient)
                .map(soupClientResponseMapper);
    }

    @Transactional
    public Optional<CreateClientWithoutAppResponse> createNewClientWithoutAppInfo(CreateClientWithoutAppDto dto) {
        Mandator mandator = mandatorRepository.findByCountryCode(dto.mandator())
                .orElseThrow(() -> new MandatorNotFoundException(dto.mandator()));

        SoupClient createdClient = SoupClient.builder()
                .clientId(clientIdGenerator.generate())
                .clientSecret(ClientSecretGenerator.generate())
                .registered(false)
                .scope(dto.scope())
                .mandator(mandator)
                .build();

        clientRepository.save(createdClient);

        return Optional.of(createdClient)
                .map(soupClientResponseWithoutAppMapper);
    }

    @Transactional
    public Optional<String> rotateClientSecret(ClientWithIdAndPasswordDto dto) {
        if (
                clientRepository.existsByClientIdAndClientSecret(dto.clientId(), dto.clientSecret())
        ) {
            String newSecret = ClientSecretGenerator.generate();
            clientRepository.updateClientSecretByClientId(dto.clientId(), newSecret);
            return Optional.of("Client-secret updated. New secret " + newSecret + " for client: " + dto.clientId());
        }

        return Optional.of("ClientSecret does not match clientId. Please enter correct secret");
    }

    @Transactional
    public Optional<String> removeApplicationFromClient(ClientWithIdAndPasswordDto dto) {
        SoupClient client = clientRepository.findByClientIdAndClientSecret(dto.clientId(), dto.clientSecret())
                .orElseThrow(() -> new ClientNotFoundException(dto.clientId()));

        clientRepository.updateApplicationMetaAndRegisteredByClientId(client.getClientId(), null, false);

        return Optional.of("Application unbound from client: " + client.getClientId());
    }

    public Optional<String> bindApplicationToClient(BindAppToClientDto dto) {
        SoupClient client = clientRepository.findByClientIdAndClientSecret(dto.clientId(), dto.clientSecret())
                .orElseThrow(() -> new ClientNotFoundException(dto.clientId()));

        if(client.isRegistered()) {
            throw new ClientAlreadyRegisteredException();
        }

        ApplicationMeta application = applicationRepository.findByUri(dto.appUri())
                .orElseThrow(() -> new ApplicationNotRegisteredException(dto.appUri()));

        client.setApplicationMeta(application);
        client.setRegistered(true);

        clientRepository.save(client);

        return Optional.of("Application \"" + dto.appUri() + "\" bound to client: " + dto.clientId());
    }
}
