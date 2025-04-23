package com.alltimeschool.apikey.config;

import com.alltimeschool.apikey.dto.ClientDetailsDto;
import com.alltimeschool.apikey.repository.ClientDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
@Component
public class DatabaseRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientDetailsRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseRegisteredClientRepository(ClientDetailsRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Saving clients is not supported.");
    }

    @Override
    public RegisteredClient findById(String id) {
        return repository.findById(id)
                .map(ClientDetailsDto::new)  // Convert to DTO first
                .map(this::convertToRegisteredClient)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return repository.findByClientId(clientId)
                .map(ClientDetailsDto::new)  // Convert to DTO first
                .map(this::convertToRegisteredClient)
                .orElse(null);
    }

    private RegisteredClient convertToRegisteredClient(ClientDetailsDto client) {
        return RegisteredClient.withId(client.getId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret()) // Assume already encoded
                .clientAuthenticationMethods(auth -> Arrays.stream(client.getAuthMethods().split(","))
                        .map(ClientAuthenticationMethod::new)
                        .forEach(auth::add))
                .authorizationGrantTypes(grant -> Arrays.stream(client.getGrantTypes().split(","))
                        .map(AuthorizationGrantType::new)
                        .forEach(grant::add))
                .redirectUris(uris -> uris.addAll(Arrays.asList(client.getRedirectUris().split(","))))
                .scopes(scope -> scope.addAll(Arrays.asList(client.getScopes().split(","))))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofSeconds(client.getAccessTokenLifetime()))
                        .refreshTokenTimeToLive(Duration.ofSeconds(client.getRefreshTokenLifetime()))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(client.isRequireAuthorizationConsent())
                        .build())
                .build();
    }
}
