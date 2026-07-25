package com.codeForLearn.live_chat_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * UserResponseDTO
 * ------------------------------------------------------------
 * Returns user details to the frontend.
 *
 * IMPORTANT:
 * Password is intentionally excluded for security reasons.
 * ============================================================
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    /**
     * User ID.
     */
    private Long id;

    /**
     * Username.
     */
    private String username;

    /**
     * Email address.
     */
    private String email;

    /**
     * Profile image URL.
     */
    private String profileImage;

    /**
     * Online status.
     */
    private Boolean online;

}