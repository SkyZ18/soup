package com.skyz.api.core.config;

import com.skyz.api.core.businessLogic.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientIdGenerator {

    private final ClientRepository clientRepository;

    public ClientIdGenerator(
            final ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
    }

    public String generate() {
        List<String> clientIds = clientRepository.findAllClientIds();

        String generatedId;
        do {
            generatedId = UUID.randomUUID().toString();
        } while (clientIds.contains(generatedId));

        return generatedId;
    }
}
