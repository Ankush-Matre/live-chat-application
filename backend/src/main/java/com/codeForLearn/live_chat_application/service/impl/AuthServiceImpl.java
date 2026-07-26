package com.codeForLearn.live_chat_application.service.impl;

import com.codeForLearn.live_chat_application.security.jwt.JwtService;
import com.codeForLearn.live_chat_application.constants.RoleConstants;
import com.codeForLearn.live_chat_application.dto.AuthResponseDTO;
import com.codeForLearn.live_chat_application.dto.LoginRequestDTO;
import com.codeForLearn.live_chat_application.dto.RegisterRequestDTO;
import com.codeForLearn.live_chat_application.entity.Role;
import com.codeForLearn.live_chat_application.entity.User;
import com.codeForLearn.live_chat_application.payload.ApiResponse;
import com.codeForLearn.live_chat_application.repository.RoleRepository;
import com.codeForLearn.live_chat_application.repository.UserRepository;
import com.codeForLearn.live_chat_application.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * ============================================================
     * JWT Service
     *
     * Responsible for generating JWT tokens after successful login.
     * ============================================================
     */
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * ============================================================
     * Register New User
     * ============================================================
     */
    @Override
    public ApiResponse register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse<>(false, "Username already exists.", null);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse<>(false, "Email already exists.", null);
        }

        Role role = roleRepository.findByName(RoleConstants.USER)
                .orElseThrow(() ->
                        new RuntimeException("Default role not found."));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);

        return new ApiResponse<>(
                true,
                "User registered successfully.",
                null
        );
    }

    /**
     * ============================================================
     * Login User
     *
     * Steps:
     * 1. Find user by username.
     * 2. Verify password using BCrypt.
     * 3. Generate JWT token.
     * 4. Return authentication response.
     * ============================================================
     */
    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password."));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getUsername());

        // Return response
        return AuthResponseDTO.builder()
                .message("Login Successful")
                .username(user.getUsername())
                .token(token)
                .build();
    }

}