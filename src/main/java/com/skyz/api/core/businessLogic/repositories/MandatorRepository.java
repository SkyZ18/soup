package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.SoupClientMandator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandatorRepository extends JpaRepository<SoupClientMandator, Long> {
    Optional<SoupClientMandator> findByCountry(String mandator);
}
