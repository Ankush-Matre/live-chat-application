package com.codeForLearn.live_chat_application.service;

import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.payload.ApiResponse;

/**
 * ===========================================================
 * Authentication Service
 *
 * Handles all authentication related business logic.
 * ===========================================================
 */
public interface AuthService {

    /**
     * Register a new user.
     *
     * @param request Registration details
     * @return API response
     */
    ApiResponse<?> register(RegisterRequestDTO request);

}