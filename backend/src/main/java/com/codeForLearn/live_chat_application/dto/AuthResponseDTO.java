package com.codeForLearn.live_chat_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * AuthResponseDTO
 * ------------------------------------------------------------
 * Returned after successful authentication.
 *
 * Currently we are returning only a success message.
 *
 * Later this class will also contain:
 * - JWT Token
 * - Username
 * - User Role
 * - Token Expiry
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

}