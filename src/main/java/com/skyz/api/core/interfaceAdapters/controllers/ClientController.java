package com.skyz.api.core.interfaceAdapters.controllers;

import com.skyz.api.core.businessLogic.services.ClientService;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientDto;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateClientResponse> register(
            @RequestBody CreateClientDto createClientDto
    ) {
        return clientService.createNewClientWithAppInfo(createClientDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
