package com.skyz.api.clientLogic.services;

import com.skyz.api.clientLogic.config.ClientSecretGenerator;
import com.skyz.api.clientLogic.dtos.requests.CreateSoupClient;
import com.skyz.api.clientLogic.models.SoupAuthClient;
import com.skyz.api.clientLogic.repositories.ApplicationMetaRepository;
import com.skyz.api.clientLogic.repositories.AuthClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AuthClientService {

    @Autowired
    private AuthClientRepository authClientRepository;
    @Autowired
    private ApplicationMetaRepository applicationMetaRepository;

    public Optional<String> registerNewAuthClient(CreateSoupClient createSoupClient) {
        log.info("Creating new Client");
        SoupAuthClient sac = new SoupAuthClient();

        var newClientId = UUID.randomUUID().toString();

        while (authClientRepository.findByClientID(newClientId) != null) {
            newClientId = UUID.randomUUID().toString();
        }

        sac.setClientID(newClientId);
        sac.setClientSecret(ClientSecretGenerator.generate());
        sac.setScope(createSoupClient.getScope());
        sac.setRegistered(false);

        authClientRepository.save(sac);

        log.info("Client created: {}, {}", sac.getClientID(), sac.getScope());

        return Optional.of("New auth client created with ID: " + newClientId);
    }

    public Optional<String> registerApplicationToAuthClient(String clientId, String appIdentity) {
        SoupAuthClient obj = authClientRepository.findByClientID(clientId);

        if (obj.isRegistered()) {
            if (obj.getAppMeta().getIdentity().equals(appIdentity)) {
                return Optional.of("Client already registered to this application");
            }
            return Optional.of("Client already registered to another application");
        }

        return registerToId(clientId, appIdentity);
    }

    public Optional<String> registerToId(String clientId, String appIdentity) {
        var sac = authClientRepository.findByClientID(clientId);
        var app = applicationMetaRepository.findByIdentity(appIdentity);

        if (app != null) {
            sac.setRegistered(true);
            sac.setAppMeta(app);

            authClientRepository.save(sac);
            return Optional.of("Registered new application to client" + sac.getClientID());
        } else {
            return Optional.of("Application not found, please create first, then proceed");
        }

    }
}
