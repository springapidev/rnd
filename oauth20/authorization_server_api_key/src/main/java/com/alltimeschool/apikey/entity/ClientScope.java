package com.alltimeschool.apikey.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_scopes")
public class ClientScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scope", nullable = false)
    private String scope;

    public ClientScope() {
    }

    public ClientScope(String scope) {
        this.scope = scope;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
