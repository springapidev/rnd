package com.alltimeschool.customtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenRepository tokenRepository;

    public MyUser registerUser(String username, String password, String role) {
        String hashedPassword = passwordEncoder.encode(password); // Hash password with BCrypt
        MyUser newUser = new MyUser();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword);
        newUser.setRole(role == null ? "USER" : role);  // Default role or based on your logic
        newUser.setStatus(true);  // Mark user as active upon registration
        return userRepository.save(newUser);
    }
    public String login(String username, String password) {
        Optional<MyUser> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        MyUser user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        // Generate custom token
        Token token = tokenService.generateToken(user);

        // Save token to DB
        tokenRepository.save(token);

        // Return token to user
        return token.getToken();
    }

    public void logout(String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token tokenRecord = tokenOpt.get();
            tokenRecord.setRevoked(true);
            tokenRepository.save(tokenRecord);  // Mark the token as revoked
        }
    }
}
