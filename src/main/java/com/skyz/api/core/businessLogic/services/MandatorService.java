package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.MandatorRepository;
import com.skyz.api.core.config.exceptions.InvalidMandatorException;
import com.skyz.api.core.config.exceptions.MandatorAlreadyExistsException;
import com.skyz.api.core.config.exceptions.MandatorNotFoundException;
import com.skyz.api.core.interfaceAdapters.dtos.CreateMandatorDto;
import com.skyz.api.core.models.Mandator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MandatorService {

    private final MandatorRepository mandatorRepository;

    public MandatorService(
            final MandatorRepository mandatorRepository
    ) {
        this.mandatorRepository = mandatorRepository;
    }

    public Optional<List<Mandator>> getAllMandators() {
        return Optional.of(mandatorRepository.findAll());
    }

    public Optional<String> createNewMandator(CreateMandatorDto dto) {
        if(dto.countryCode().isBlank() || dto.country().isBlank()) {
            throw new InvalidMandatorException();
        }

        if(mandatorRepository.existsByCountryCode(dto.countryCode())) {
            throw new MandatorAlreadyExistsException(dto.countryCode());
        }

        Mandator mandator = Mandator.builder()
                .country(dto.country())
                .countryCode(dto.countryCode())
                .build();

        mandatorRepository.save(mandator);

        return Optional.of("Created new Mandator: " + mandator.getCountryCode());
    }
}
