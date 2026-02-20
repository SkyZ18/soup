package com.skyz.api.core.businessLogic.repositories;

import com.skyz.api.core.models.ApplicationMeta;
import com.skyz.api.core.models.SoupClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<SoupClient, Long> {
    boolean existsByClientId(String id);

    boolean existsByApplicationMeta_Uri(String uri);

    boolean existsByClientIdAndClientSecret(String clientId, String clientSecret);

    @Query("SELECT c.clientId FROM SoupClient c")
    List<String> findAllClientIds();

    List<SoupClient> findByRegistered(boolean registered);

    Optional<SoupClient> findByClientIdAndClientSecret(String clientId, String clientSecret);

    @Modifying
    @Query("UPDATE SoupClient c SET c.clientSecret = :secret WHERE c.clientId = :clientId")
    void updateClientSecretByClientId(
            @Param("clientId") String clientId,
            @Param("secret") String secret
    );

    @Modifying
    @Query("UPDATE SoupClient c SET c.applicationMeta = :applicationMeta, c.registered = :registered WHERE c.clientId = :clientId")
    void updateApplicationMetaAndRegisteredByClientId(
            @Param("clientId") String clientId,
            @Param("applicationMeta") ApplicationMeta applicationMeta,
            @Param("registered") boolean registered
    );

}
