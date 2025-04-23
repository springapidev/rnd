package com.alltimeschool.apikey.repository;

import com.alltimeschool.apikey.entity.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {
    Optional<ClientDetails> findByClientId(String clientId);
}
