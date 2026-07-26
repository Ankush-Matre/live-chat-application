package com.codeForLearn.live_chat_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * AuthResponseDTO
 * ------------------------------------------------------------
 * Standard response returned after authentication operations.
 *
 * Used for:
 * - User Registration
 * - User Login
 *
 * During Registration:
 * - message
 *
 * During Login:
 * - message
 * - JWT Token
 * - username
 *
 * (More fields like role, expiry, refresh token can be
 * added later.)
 * ============================================================
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    /**
     * Response message.
     */
    private String message;

    /**
     * JWT Token.
     * Null during registration.
     */
    private String token;

    /**
     * Logged-in username.
     * Null during registration.
     */
    private String username;

}