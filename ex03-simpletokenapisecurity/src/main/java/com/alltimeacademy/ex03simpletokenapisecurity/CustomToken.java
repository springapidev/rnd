package com.alltimeacademy.ex03simpletokenapisecurity;

public class CustomToken {
    private String token;

    public CustomToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
