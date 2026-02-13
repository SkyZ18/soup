package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.SoupClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<SoupClient, Long> {
    SoupClient findByUri(String s);

    boolean existsByClientId(String id);
}
