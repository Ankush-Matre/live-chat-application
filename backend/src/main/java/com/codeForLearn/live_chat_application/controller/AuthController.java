package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.payload.ApiResponse;
import com.codeForLearn.live_chat_application.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ===========================================================
 * Authentication Controller
 *
 * Handles all authentication APIs.
 * ===========================================================
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor Injection
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user.
     *
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(
            @RequestBody RegisterRequestDTO request) {

        ApiResponse<?> response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}