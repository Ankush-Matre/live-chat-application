package com.codeForLearn.live_chat_application.service;

import com.codeForLearn.live_chat_application.dto.AuthResponseDTO;
import com.codeForLearn.live_chat_application.dto.LoginRequestDTO;
import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.payload.ApiResponse;

/**
 * ============================================================
 * AuthService
 * ------------------------------------------------------------
 * Service interface responsible for authentication operations.
 *
 * Current Features:
 * - User Registration
 * - User Login
 *
 * Future Features:
 * - Refresh Token
 * - Forgot Password
 * - Reset Password
 * ============================================================
 */
public interface AuthService {

    /**
     * ============================================================
     * Registers a new user.
     *
     * @param request Registration details
     * @return Success or failure response
     * ============================================================
     */
    ApiResponse register(RegisterRequestDTO request);

    /**
     * ============================================================
     * Authenticates an existing user.
     *
     * @param request Login credentials
     * @return JWT Token with user information
     * ============================================================
     */
    AuthResponseDTO login(LoginRequestDTO request);

}