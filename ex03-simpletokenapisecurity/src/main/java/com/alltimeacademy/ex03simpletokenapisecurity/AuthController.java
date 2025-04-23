package com.alltimeacademy.ex03simpletokenapisecurity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<CustomToken> login(@RequestBody UserCredentials credentials) {
        System.out.println("Landed Here");
        // Authenticate the user (simple check for demo purposes)
        if ("user".equals(credentials.getUsername()) && "password".equals(credentials.getPassword())) {
            // Generate a custom token (for demo purposes, we'll return a fixed token)
            CustomToken token = new CustomToken("valid-custom-token");
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

