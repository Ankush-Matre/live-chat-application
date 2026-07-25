package com.codeForLearn.live_chat_application.service.impl;

import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.entity.Role;
import com.codeForLearn.live_chat_application.entity.User;
import com.codeForLearn.live_chat_application.payload.ApiResponse;
import com.codeForLearn.live_chat_application.repository.RoleRepository;
import com.codeForLearn.live_chat_application.repository.UserRepository;
import com.codeForLearn.live_chat_application.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ===========================================================
 * AuthServiceImpl
 *
 * Handles all authentication related business logic.
 *
 * Current Features:
 * 1. User Registration
 *
 * Future Features:
 * 2. Login
 * 3. JWT Token Generation
 * 4. Refresh Token
 * ===========================================================
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor Injection
     */
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * ===========================================================
     * Register New User
     * ===========================================================
     */
    @Override
    public ApiResponse register(RegisterRequestDTO request) {

        /*
         * Check whether username already exists
         */
        if (userRepository.existsByUsername(request.getUsername())) {

            return ApiResponse.builder()
                    .success(false)
                    .message("Username already exists.")
                    .build();
        }

        /*
         * Check whether email already exists
         */
        if (userRepository.existsByEmail(request.getEmail())) {

            return ApiResponse.builder()
                    .success(false)
                    .message("Email already registered.")
                    .build();
        }

        /*
         * Fetch ROLE_USER from database
         */
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() ->
                        new RuntimeException("ROLE_USER not found in database."));

        /*
         * Create User Entity
         */
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())

                // Encrypt password before saving
                .password(passwordEncoder.encode(request.getPassword()))

                .role(userRole)

                .online(false)

                .build();

        /*
         * Save User into Database
         */
        userRepository.save(user);

        /*
         * Return Success Response
         */
        return ApiResponse.builder()
                .success(true)
                .message("User registered successfully.")
                .build();
    }
}