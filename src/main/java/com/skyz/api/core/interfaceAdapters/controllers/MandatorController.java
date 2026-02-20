package com.skyz.api.core.interfaceAdapters.controllers;

import com.skyz.api.core.businessLogic.services.MandatorService;
import com.skyz.api.core.interfaceAdapters.dtos.CreateMandatorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mandator")
public class MandatorController {

    private final MandatorService mandatorService;

    public MandatorController(
            final MandatorService mandatorService
    ) {
        this.mandatorService = mandatorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestBody CreateMandatorDto createMandatorDto
    ) {
        return mandatorService.createNewMandator(createMandatorDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
    }
}
