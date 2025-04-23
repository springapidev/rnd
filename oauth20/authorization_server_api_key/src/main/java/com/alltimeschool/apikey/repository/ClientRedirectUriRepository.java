package com.alltimeschool.apikey.repository;

import com.alltimeschool.apikey.entity.ClientRedirectUri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRedirectUriRepository extends JpaRepository<ClientRedirectUri,Long> {
}
