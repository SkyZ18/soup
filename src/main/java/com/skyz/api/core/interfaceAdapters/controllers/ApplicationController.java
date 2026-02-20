package com.skyz.api.core.interfaceAdapters.controllers;

import com.skyz.api.core.businessLogic.services.ApplicationService;
import com.skyz.api.core.interfaceAdapters.dtos.CreateApplicationDto;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            final ApplicationService applicationService
    ) {
        this.applicationService = applicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateApplicationResponse> register(
            @RequestBody CreateApplicationDto createApplicationDto
            ) {
        return applicationService.createNewApplication(createApplicationDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
