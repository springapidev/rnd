package com.alltimeschool.jwttoken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "123456";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final SecretKey signingKey;
    private final JwtParser jwtParser;

    public JwtUtil() {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        this.jwtParser = Jwts.parser().verifyWith(signingKey).build(); // Correct new API usage
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey) // Uses the new API correctly
                .compact();
    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public boolean validateToken(String token, String username) {
        try {
            return extractUsername(token).equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return parseToken(token).getPayload().getExpiration().before(new Date());
    }

    private Jws<Claims> parseToken(String token) {
        return jwtParser.parseSignedClaims(token); // Correct way to parse JWT in JJWT 0.12.6
    }
}
