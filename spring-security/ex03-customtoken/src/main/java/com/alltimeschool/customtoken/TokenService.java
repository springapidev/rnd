package com.alltimeschool.customtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean isValidToken(String token) {
        if (token == null || token.isEmpty()) {
            return false; // Token is missing or empty
        }

        // 1. Validate token format
        if (!isValidFormat(token)) {
            return false; // Invalid token format
        }

        // 2. Retrieve token from the repository
        Optional<Token> tokenRecordOpt = tokenRepository.findByToken(token);
        if (!tokenRecordOpt.isPresent()) {
            return false; // Token doesn't exist in the database
        }

        Token tokenRecord = tokenRecordOpt.get();

        // 3. Token expiration check
        if (isExpired(tokenRecord)) {
            return false; // Token has expired
        }

        // 4. Token revocation check
        if (isRevoked(tokenRecord)) {
            return false; // Token has been revoked
        }

        // 5. Validate token ownership (check if token belongs to an active user)
        if (!isTokenOwnedByValidByUser(tokenRecord)) {
            return false; // Invalid user associated with the token
        }

        // 6. User account status check (suspended/locked)
        MyUser user = userRepository.findById(tokenRecord.getUserId()).orElse(null);
        if (user != null && !isUserActive(user)) {
            return false; // User is not active
        }

        // 7. Scope/permission check
        if (!hasRequiredScope(tokenRecord, "read")) { // Example: "read" scope
            return false; // Token does not have the required permissions
        }

        // 8. Tampering detection (if applicable)
        if (isTokenTampered(tokenRecord)) {
            return false; // Token has been tampered with
        }

        // 9. Token creation time check
        if (isTokenCreatedTooLongAgo(tokenRecord)) {
            return false; // Token was created too long ago
        }

        // 10. Rate-limiting check
        if (isRateLimited(token)) {
            return false; // Too many requests with this token
        }

        // 11. Replay attack detection
        if (isTokenUsedInReplayAttack(tokenRecord)) {
            return false; // Token has been used in a replay attack
        }

        // If all checks pass, the token is valid
        return true;
    }

    // 1. Token Format Validation
    private boolean isValidFormat(String token) {
        // Example: Token should be 64 characters long (can be adjusted to your format)
        return token.length() == 64;
    }

    //Token Expiration
    private boolean isExpired(Token tokenRecord) {
        return tokenRecord.getExpirationDate().isBefore(Instant.now());
    }

    // 3. Tokens may be revoked manually by an administrator or through some other business logic.
    private boolean isRevoked(Token tokenRecord) {
        return tokenRecord.isRevoked();
    }

    //4. Token Ownership (User Validation)
    //Each token is associated with a user. We check if the token belongs to a valid, active user.
    private boolean isTokenOwnedByValidByUser(Token tokenRecord) {
        Optional<MyUser> userOptional = userRepository.findById(tokenRecord.getUserId());
        if (userOptional.isEmpty()) {
            return false;
        }
        MyUser user = userOptional.get();
        return !user.isSuspended() && !user.isLocked();
    }

    // User Account Status Check (Suspended/Locked)
    //A token is only valid if the user associated with it has an active account (not suspended or locked).
    private boolean isUserActive(MyUser user) {
        return user.isStatus();
    }

    // 6. Scope / Permission Check
    //If your tokens have scopes or permissions (e.g., “admin”, “read”, “write”), you should ensure that the token has the necessary permissions for the requested operation.
    private boolean hasRequiredScope(Token tokenRecord, String requiredScope) {
        return tokenRecord.getScope().contains(requiredScope); // Check if token has required scope
    }

    // 7. Tampering Detection (Integrity Check)
//If you generate tokens with a cryptographic signature or hash (e.g., HMAC), you should check whether the token has been tampered with. This can be done by verifying a signature or comparing hashes.
    private boolean isTokenTampered(Token tokenRecord) {
        String expectedHmac = generateHmac(tokenRecord.getTokenData(), "123456");
        return !expectedHmac.equals(tokenRecord.getHash()); // Compare expected hash with stored hash
    }

    private String generateHmac(String tokenData, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmac = mac.doFinal(tokenData.getBytes());
            return Base64.getEncoder().encodeToString(hmac); // Return the encoded HMAC
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC", e);
        }
    }


    // 8. Token Creation Time Check
    private boolean isTokenCreatedTooLongAgo(Token tokenRecord) {
        Instant oneHourAgo = Instant.now().minusSeconds(3600); // 1 hour ago
        return tokenRecord.getCreationDate().isBefore(oneHourAgo);
    }

    // 9. Rate-Limiting / Abuse Detection
    // Detect and prevent abuse by ensuring that the same token isn't used too frequently (e.g., too many requests in a short period). You can store token usage counts in a cache (e.g., Redis) and enforce limits.
    private boolean isRateLimited(String token) {
        int usageCount = getTokenUsageCount(token); // Implement logic to retrieve usage count
        return usageCount > 100; // Limit to 100 requests (for example)
    }

    private int getTokenUsageCount(String token) {
        // Retrieve the token's usage count from a cache or database
        return 0; // Placeholder for actual logic
    }

    // 10. Replay Attack Detection
    // Prevent replay attacks by ensuring that the token is used only once per request, or use a mechanism such as a nonce to prevent the same token from being reused.
    private boolean isTokenUsedInReplayAttack(Token tokenRecord) {
        // Implement logic to detect replay attacks, e.g., by checking if the token was already used recently
        return false; // Placeholder for actual logic
    }

    public Token generateToken(MyUser user) {
        // Create token data with user id, role, and expiration time
        String tokenData = user.getId() + ":" + user.getRole() + ":" + Instant.now().plusSeconds(3600).toString(); // Example token data
        String tokenHash = generateHmac(tokenData, "123456"); // Generate HMAC for token data

        // Create token object to store
        Token token = new Token();
        token.setUserId(user.getId()); // Associate the token with the user
        token.setToken(tokenData); // Store raw token data (for example, user ID + role + expiration)
        token.setExpirationDate(Instant.now().plusSeconds(3600)); // Token expiration date (1 hour)
        token.setCreationDate(Instant.now()); // Token creation date
        token.setHash(tokenHash); // Store HMAC hash to check for tampering later
        token.setRevoked(false); // Set token as not revoked by default
        token.setScope("read,write"); // Define scope (permissions) for the token

        return token;
    }

}
