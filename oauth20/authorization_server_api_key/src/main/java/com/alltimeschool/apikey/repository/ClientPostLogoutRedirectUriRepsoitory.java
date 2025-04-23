package com.alltimeschool.apikey.repository;

import com.alltimeschool.apikey.entity.ClientPostLogoutRedirectUri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPostLogoutRedirectUriRepsoitory extends JpaRepository<ClientPostLogoutRedirectUri,Long> {
}
