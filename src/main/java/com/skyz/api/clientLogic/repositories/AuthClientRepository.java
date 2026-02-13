package com.skyz.api.clientLogic.repositories;

import com.skyz.api.clientLogic.models.SoupAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthClientRepository extends JpaRepository<SoupAuthClient, Long> {
    SoupAuthClient findByClientID(String clientID);
}
