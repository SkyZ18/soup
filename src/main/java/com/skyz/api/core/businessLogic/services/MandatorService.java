package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.MandatorRepository;
import com.skyz.api.core.config.exceptions.MandatorAlreadyExistsException;
import com.skyz.api.core.interfaceAdapters.dtos.CreateMandatorDto;
import com.skyz.api.core.models.Mandator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MandatorService {

    private final MandatorRepository mandatorRepository;

    public MandatorService(
            final MandatorRepository mandatorRepository
    ) {
        this.mandatorRepository = mandatorRepository;
    }

    public Optional<String> createNewMandator(CreateMandatorDto createMandatorDto) {
        if(mandatorRepository.existsByCountryCode(createMandatorDto.countryCode())) {
            throw new MandatorAlreadyExistsException(createMandatorDto.countryCode());
        }

        Mandator mandator = Mandator.builder()
                .country(createMandatorDto.country())
                .countryCode(createMandatorDto.countryCode())
                .build();

        mandatorRepository.save(mandator);

        return Optional.of("Created new Mandator: " + mandator.getCountryCode());
    }
}
