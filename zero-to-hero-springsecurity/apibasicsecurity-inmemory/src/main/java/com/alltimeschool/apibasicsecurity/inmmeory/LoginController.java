package com.alltimeschool.apibasicsecurity.inmmeory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class LoginController {
    @GetMapping(value = "/login")
    public String login(@RequestParam String username, String password){
        String token = username + ":" + password;
        return Base64.getEncoder().encodeToString(token.getBytes());
    }
}
