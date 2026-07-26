package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.AuthResponseDTO;
import com.codeForLearn.live_chat_application.dto.LoginRequestDTO;
import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.payload.ApiResponse;
import com.codeForLearn.live_chat_application.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ===========================================================
 * Authentication Controller
 * -----------------------------------------------------------
 * Handles all authentication-related REST APIs.
 *
 * Available APIs:
 *
 * 1. POST /api/auth/register
 *    - Registers a new user.
 *
 * 2. POST /api/auth/login
 *    - Authenticates an existing user.
 *    - Returns a JWT token upon successful login.
 *
 * Future APIs:
 * - Forgot Password
 * - Reset Password
 * - Refresh Token
 * - Logout
 * ===========================================================
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * ===========================================================
     * Constructor Injection
     *
     * Spring injects the AuthService automatically.
     * ===========================================================
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * ===========================================================
     * Register User
     *
     * URL:
     * POST /api/auth/register
     *
     * Request Body:
     * {
     *   "username":"ankush",
     *   "email":"ankush@gmail.com",
     *   "password":"Password@123"
     * }
     *
     * Response:
     * {
     *   "success": true,
     *   "message": "User registered successfully.",
     *   "data": null
     * }
     * ===========================================================
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(
            @RequestBody RegisterRequestDTO request) {

        ApiResponse<?> response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * ===========================================================
     * Login User
     *
     * URL:
     * POST /api/auth/login
     *
     * Request Body:
     * {
     *   "username":"ankush",
     *   "password":"Password@123"
     * }
     *
     * Response:
     * {
     *   "message":"Login Successful",
     *   "token":"eyJhbGciOiJIUzI1NiJ9...",
     *   "username":"ankush"
     * }
     * ===========================================================
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(
            @RequestBody LoginRequestDTO request) {

        AuthResponseDTO response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}