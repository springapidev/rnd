package com.alltimeschool.apikey.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_post_logout_redirect_uris")
public class ClientPostLogoutRedirectUri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "post_logout_redirect_uri", nullable = false)
    private String postLogoutRedirectUri;

    // Constructors, Getters, and Setters


    public ClientPostLogoutRedirectUri() {
    }

    public ClientPostLogoutRedirectUri(String postLogoutRedirectUri) {
        this.postLogoutRedirectUri = postLogoutRedirectUri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostLogoutRedirectUri() {
        return postLogoutRedirectUri;
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        this.postLogoutRedirectUri = postLogoutRedirectUri;
    }
}
