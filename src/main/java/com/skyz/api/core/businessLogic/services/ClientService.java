package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.ApplicationRepository;
import com.skyz.api.core.businessLogic.repositories.ClientRepository;
import com.skyz.api.core.businessLogic.repositories.MandatorRepository;
import com.skyz.api.core.config.ClientIdGenerator;
import com.skyz.api.core.config.ClientSecretGenerator;
import com.skyz.api.core.config.exceptions.ApplicationAlreadyRegisteredException;
import com.skyz.api.core.config.exceptions.ApplicationNotRegisteredException;
import com.skyz.api.core.config.exceptions.ClientNotFoundException;
import com.skyz.api.core.config.exceptions.MandatorNotFoundException;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithoutAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.ClientWithIdAndPasswordDto;
import com.skyz.api.core.interfaceAdapters.dtos.mappers.SoupClientResponseMapper;
import com.skyz.api.core.interfaceAdapters.dtos.mappers.SoupClientResponseWithoutAppMapper;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientWithoutAppResponse;
import com.skyz.api.core.models.ApplicationMeta;
import com.skyz.api.core.models.SoupClient;
import com.skyz.api.core.models.SoupClientMandator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

    public List<SoupClient> returnAllClientsWithSecret() {
        return clientRepository.findAll();
    }

    @Transactional
    public Optional<CreateClientResponse> createNewClientWithAppInfo(CreateClientWithAppDto dto) {
        log.info("Starting client creation Process");
        ApplicationMeta application = applicationRepository.findByUri(dto.registerUri())
                .orElseThrow(() -> new ApplicationNotRegisteredException(dto.registerUri()));

        if (clientRepository.existsByApplicationMeta_Uri(application.getUri())) {
            throw new ApplicationAlreadyRegisteredException(application.getUri());
        }

        SoupClientMandator mandator = mandatorRepository.findByCountry(dto.mandator())
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
        SoupClientMandator mandator = mandatorRepository.findByCountry(dto.mandator())
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
}
