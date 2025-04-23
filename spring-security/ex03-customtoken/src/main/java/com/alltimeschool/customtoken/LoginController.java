package com.alltimeschool.customtoken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService; // Service for generating tokens

    @Autowired
    private PasswordEncoder passwordEncoder; // BCrypt password encoder
    @PostMapping("/signup")
    public ResponseEntity<?> save(@Valid @RequestBody MyUser myUser) {
    MyUser user=this.userService.registerUser(myUser.getUsername(), myUser.getPassword(), myUser.getRole());
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        // Check if the user exists
        MyUser user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check if the password matches the stored password (using BCrypt)
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Create the token for the user
        String token = tokenService.generateToken(user).getToken();

        // Return the token as part of the response
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Helper classes for request and response
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
