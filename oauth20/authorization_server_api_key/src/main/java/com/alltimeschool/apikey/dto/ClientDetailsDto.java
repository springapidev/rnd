package com.alltimeschool.apikey.dto;

import com.alltimeschool.apikey.entity.ClientDetails;
import com.alltimeschool.apikey.entity.ClientRedirectUri;
import com.alltimeschool.apikey.entity.ClientScope;

import java.util.stream.Collectors;

public class ClientDetailsDto {
    private String id;
    private String clientId;
    private String clientSecret;
    private String grantTypes;
    private String authMethods;
    private String redirectUris;
    private String scopes;
    private boolean enabled;
    private boolean requireAuthorizationConsent;
    private Integer accessTokenLifetime;
    private Integer refreshTokenLifetime;

    public ClientDetailsDto(ClientDetails client) {
        this.id = client.getId().toString();
        this.clientId = client.getClientId();
        this.clientSecret = client.getClientSecret();
        this.grantTypes = client.getGrantTypes().stream()
                .map(Enum::name) // Convert Enum to String
                .collect(Collectors.joining(",")); // Join as comma-separated string
        this.authMethods = client.getTokenEndpointAuthMethods().stream()
                .map(Enum::name) // Convert Enum to String
                .collect(Collectors.joining(",")); // Join as comma-separated string
        this.redirectUris = client.getRedirectUris().stream()
                .map(ClientRedirectUri::getRedirectUri) // Extract URI as String
                .collect(Collectors.joining(","));
        this.scopes = client.getScopes().stream()
                .map(ClientScope::getScope) // Extract scope as String
                .collect(Collectors.joining(","));
        this.enabled = client.isEnabled();
        this.requireAuthorizationConsent = client.isRequireAuthorizationConsent();
        this.accessTokenLifetime = client.getAccessTokenLifetime();
        this.refreshTokenLifetime = client.getRefreshTokenLifetime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(String authMethods) {
        this.authMethods = authMethods;
    }

    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public void setRequireAuthorizationConsent(boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }

    public Integer getAccessTokenLifetime() {
        return accessTokenLifetime;
    }

    public void setAccessTokenLifetime(Integer accessTokenLifetime) {
        this.accessTokenLifetime = accessTokenLifetime;
    }

    public Integer getRefreshTokenLifetime() {
        return refreshTokenLifetime;
    }

    public void setRefreshTokenLifetime(Integer refreshTokenLifetime) {
        this.refreshTokenLifetime = refreshTokenLifetime;
    }
}
