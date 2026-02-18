package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.ApplicationMeta;
import com.skyz.api.core.models.SoupClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<SoupClient, Long> {
    boolean existsByClientId(String id);

    boolean existsByApplicationMeta_Uri(String uri);

    boolean existsByClientIdAndClientSecret(String clientId, String clientSecret);

    void updateClientSecretByClientId(String clientId, String secret);

    Optional<SoupClient> findByClientIdAndClientSecret(String clientId, String clientSecret);

    void updateApplicationMetaAndRegisteredByClientId(String clientId, ApplicationMeta applicationMeta, boolean registered);

    @Query("SELECT c.clientId FROM SoupClient c")
    List<String> findAllClientIds();
}
