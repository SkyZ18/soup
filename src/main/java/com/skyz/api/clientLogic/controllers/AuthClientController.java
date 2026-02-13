package com.skyz.api.clientLogic.controllers;

import com.skyz.api.clientLogic.dtos.requests.CreateSoupClient;
import com.skyz.api.clientLogic.services.AuthClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authClient")
public class AuthClientController {

    private final AuthClientService authClientService;

    public AuthClientController(final AuthClientService authClientService) {
        this.authClientService = authClientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @RequestBody final CreateSoupClient createSoupClient
    ) {
        return this.authClientService.registerNewAuthClient(createSoupClient)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/app/register/{clientId}/{appIdentity}")
    public ResponseEntity<String> registerApp(
            @PathVariable final String clientId,
            @PathVariable final String appIdentity
    ) {
        return this.authClientService.registerApplicationToAuthClient(clientId, appIdentity)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
