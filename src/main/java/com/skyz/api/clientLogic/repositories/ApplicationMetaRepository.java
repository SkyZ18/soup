package com.skyz.api.clientLogic.repositories;

import com.skyz.api.clientLogic.models.ApplicationMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationMetaRepository extends JpaRepository<ApplicationMeta, Long> {
    ApplicationMeta findByAppName(String appName);
    ApplicationMeta findByIdentity(String identity);
}
