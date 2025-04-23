package com.alltimeschool.apikey.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_redirect_uris")
public class ClientRedirectUri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "redirect_uri", nullable = false)
    private String redirectUri;

    public ClientRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public ClientRedirectUri() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
// Constructors, Getters, and Setters
}
