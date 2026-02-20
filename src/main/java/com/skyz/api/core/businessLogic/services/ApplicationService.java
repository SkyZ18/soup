package com.skyz.api.core.businessLogic.services;

import com.skyz.api.core.businessLogic.repositories.ApplicationRepository;
import com.skyz.api.core.config.exceptions.ApplicationAlreadyExistsException;
import com.skyz.api.core.interfaceAdapters.dtos.CreateApplicationDto;
import com.skyz.api.core.interfaceAdapters.dtos.mappers.ApplicationResponseMapper;
import com.skyz.api.core.interfaceAdapters.dtos.responses.CreateApplicationResponse;
import com.skyz.api.core.models.ApplicationMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationResponseMapper applicationResponseMapper;

    public ApplicationService(
            final ApplicationRepository applicationRepository,
            final ApplicationResponseMapper applicationResponseMapper
    ) {
        this.applicationRepository = applicationRepository;
        this.applicationResponseMapper = applicationResponseMapper;
    }

    public Optional<CreateApplicationResponse> createNewApplication(CreateApplicationDto createApplicationDto) {
        if(applicationRepository.existsByUri(createApplicationDto.uri())) {
            throw new ApplicationAlreadyExistsException(createApplicationDto.uri());
        }

        ApplicationMeta createdApp = ApplicationMeta.builder()
                .name(createApplicationDto.name())
                .uri(createApplicationDto.uri())
                .type(createApplicationDto.type())
                .build();

        applicationRepository.save(createdApp);

        return Optional.of(createdApp)
                .map(applicationResponseMapper);
    }
}
