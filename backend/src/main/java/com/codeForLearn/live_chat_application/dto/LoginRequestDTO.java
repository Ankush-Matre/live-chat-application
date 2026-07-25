package com.codeForLearn.live_chat_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * LoginRequestDTO
 * ------------------------------------------------------------
 * Receives login credentials from the frontend.
 *
 * Request Example:
 * {
 *    "username":"ankush",
 *    "password":"123456"
 * }
 * ============================================================
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    /**
     * Username entered by the user.
     */
    private String username;

    /**
     * Password entered by the user.
     */
    private String password;

}