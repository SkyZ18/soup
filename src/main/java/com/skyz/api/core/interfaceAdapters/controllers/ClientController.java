package com.skyz.api.core.interfaceAdapters.controllers;

import com.skyz.api.core.businessLogic.services.ClientService;
import com.skyz.api.core.interfaceAdapters.dtos.ClientWithIdAndPasswordDto;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.CreateClientWithoutAppDto;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientResponse;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateClientWithoutAppResponse;
import com.skyz.api.core.models.SoupClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getAll")
    public List<SoupClient> getAll() {
        return clientService.returnAllClientsWithSecret();
    }

    @PostMapping("/register")
    public ResponseEntity<CreateClientResponse> register(
            @RequestBody CreateClientWithAppDto createClientWithAppDto
    ) {
        return clientService.createNewClientWithAppInfo(createClientWithAppDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/register/noApp")
    public ResponseEntity<CreateClientWithoutAppResponse> register(
            @RequestBody CreateClientWithoutAppDto createClientWithoutAppDto
    ) {
        return clientService.createNewClientWithoutAppInfo(createClientWithoutAppDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/update/secret")
    public ResponseEntity<String> updateSecret(
            @RequestBody ClientWithIdAndPasswordDto updateClientWithIdDto
    ) {
        return clientService.rotateClientSecret(updateClientWithIdDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/app/unbind")
    public ResponseEntity<String> unbindApp(
            @RequestBody ClientWithIdAndPasswordDto unbindDto
    ) {
        return clientService.removeApplicationFromClient(unbindDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
