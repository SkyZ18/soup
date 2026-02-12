package com.skyz.api.clientLogic.services;

import com.skyz.api.clientLogic.models.ApplicationMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ApplicationService {

    public Optional<String> createNewApplication(ApplicationMeta appMeta) {
        return Optional.of("");
    }
}
