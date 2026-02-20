package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.Mandator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandatorRepository extends JpaRepository<Mandator, Long> {
    Optional<Mandator> findByCountry(String mandator);

    Optional<Mandator> findByCountryCode(String countyCode);

    boolean existsByCountryCode(String countryCode);
}
