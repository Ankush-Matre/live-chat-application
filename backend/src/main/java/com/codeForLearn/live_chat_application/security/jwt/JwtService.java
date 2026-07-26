package com.codeForLearn.live_chat_application.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * ============================================================
 * JWT SERVICE
 * ------------------------------------------------------------
 * Responsibilities:
 * 1. Generate JWT Token
 * 2. Extract Username from Token
 * 3. Validate JWT Token
 * 4. Check Token Expiration
 *
 * NOTE:
 * This class ONLY handles JWT operations.
 * It does NOT communicate with the database.
 * ============================================================
 */
@Service
public class JwtService {

    /**
     * ============================================================
     * Secret Key
     *
     * Loaded from application.properties
     * ============================================================
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * ============================================================
     * JWT Expiration Time
     *
     * Loaded from application.properties
     * Example:
     * 86400000 = 24 Hours
     * ============================================================
     */
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * ============================================================
     * Generate JWT Token
     *
     * Creates a signed JWT containing:
     * - Username
     * - Issued Time
     * - Expiration Time
     * ============================================================
     */
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * ============================================================
     * Extract Username
     *
     * Reads username stored inside JWT.
     * ============================================================
     */
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    /**
     * ============================================================
     * Validate JWT Token
     *
     * Returns true if:
     * ✔ Username matches
     * ✔ Token is NOT expired
     * ============================================================
     */
    public boolean isTokenValid(String token, String username) {

        return username.equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    /**
     * ============================================================
     * Check whether JWT is expired.
     * ============================================================
     */
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /**
     * ============================================================
     * Extract all JWT Claims.
     * ============================================================
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * ============================================================
     * Create SecretKey from Base64 Secret.
     * ============================================================
     */
    private SecretKey getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}