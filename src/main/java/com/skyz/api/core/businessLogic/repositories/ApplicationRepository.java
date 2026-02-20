package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.ApplicationMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationMeta, Long> {
    Optional<ApplicationMeta> findByUri(String s);

    boolean existsByUri(String uri);
}
