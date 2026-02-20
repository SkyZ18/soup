package com.skyz.api.core.interfaceAdapters.controllers;

import com.skyz.api.core.businessLogic.services.MandatorService;
import com.skyz.api.core.interfaceAdapters.dtos.CreateMandatorDto;
import com.skyz.api.core.models.Mandator;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mandator")
public class MandatorController {

    private final MandatorService mandatorService;

    public MandatorController(
            final MandatorService mandatorService
    ) {
        this.mandatorService = mandatorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Mandator>> getAll() {
        return mandatorService.getAllMandators()
                .map(ResponseEntity::ok)
                .orElseThrow(RuntimeException::new);
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
