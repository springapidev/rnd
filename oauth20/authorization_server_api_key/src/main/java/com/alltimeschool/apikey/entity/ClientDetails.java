package com.alltimeschool.apikey.entity;

import com.alltimeschool.apikey.enums.AuthMethod;
import com.alltimeschool.apikey.enums.ClientResponseType;
import com.alltimeschool.apikey.enums.ClientType;
import com.alltimeschool.apikey.enums.GrantType;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "oauth2_clients")
public class ClientDetails {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

   @ManyToMany
    private List<ClientRedirectUri> redirectUris;

    @ManyToMany
    private List<ClientScope> scopes;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean requireAuthorizationConsent = true;

    @ElementCollection
    @CollectionTable(name = "client_grant_types", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "grant_type")
    private Set<GrantType> grantTypes;

    @ElementCollection
    @CollectionTable(name = "client_token_endpoint_auth_method", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "auth_method")
    @Enumerated(EnumType.STRING) // Store as string in DB
    private Set<AuthMethod> tokenEndpointAuthMethods;

    @ElementCollection
    @CollectionTable(name = "client_response_types", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "client_response_type")
    private Set<ClientResponseType> clientResponseTypes;

@ManyToMany
    private List<ClientPostLogoutRedirectUri> postLogoutRedirectUris;

    @Column(nullable = false)
    private Integer accessTokenLifetime = 3600;

    @Column(nullable = false)
    private Integer refreshTokenLifetime = 1209600;

    @Column
    private String clientName;


    @Column(nullable = false)
    private ClientType clientType = ClientType.CONFIDENTIAL;

    @Column
    private String clientDescription;

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

    public List<ClientRedirectUri> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<ClientRedirectUri> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<ClientScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<ClientScope> scopes) {
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

    public Set<GrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<GrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Set<AuthMethod> getTokenEndpointAuthMethods() {
        return tokenEndpointAuthMethods;
    }

    public void setTokenEndpointAuthMethods(Set<AuthMethod> tokenEndpointAuthMethods) {
        this.tokenEndpointAuthMethods = tokenEndpointAuthMethods;
    }

    public Set<ClientResponseType> getClientResponseTypes() {
        return clientResponseTypes;
    }

    public void setClientResponseTypes(Set<ClientResponseType> clientResponseTypes) {
        this.clientResponseTypes = clientResponseTypes;
    }

    public List<ClientPostLogoutRedirectUri> getPostLogoutRedirectUris() {
        return postLogoutRedirectUris;
    }

    public void setPostLogoutRedirectUris(List<ClientPostLogoutRedirectUri> postLogoutRedirectUris) {
        this.postLogoutRedirectUris = postLogoutRedirectUris;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }
}
