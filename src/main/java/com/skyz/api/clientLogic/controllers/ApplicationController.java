package com.skyz.api.clientLogic.controllers;

import com.skyz.api.clientLogic.models.ApplicationMeta;
import com.skyz.api.clientLogic.services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(final ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody ApplicationMeta appMeta
    ) {
        return this.applicationService.createNewApplication(appMeta)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
