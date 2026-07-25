package com.codeForLearn.live_chat_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * RegisterRequestDTO
 * ------------------------------------------------------------
 * This DTO receives user registration details from the client.
 *
 * It is used only while creating a new account.
 *
 * Request Example:
 * {
 *   "username":"ankush",
 *   "email":"ankush@gmail.com",
 *   "password":"123456"
 * }
 * ============================================================
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    /**
     * Username chosen by the user.
     */
    private String username;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Plain password received from frontend.
     *
     * NOTE:
     * It will be encrypted using BCrypt before saving into DB.
     */
    private String password;

}