package com.codeForLearn.live_chat_application.security.config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.codeForLearn.live_chat_application.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ============================================================
 * Security Configuration
 * ------------------------------------------------------------
 * Configures Spring Security for the application.
 *
 * Current Configuration:
 * - Disable CSRF
 * - Allow public authentication APIs
 * - Require authentication for all other APIs
 *
 * NOTE:
 * JWT Filter will be integrated in the next step.
 * ============================================================
 */
@Configuration
public class SecurityConfig {

    /**
     * JWT Authentication Filter.
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor Injection.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * ============================================================
     * Security Filter Chain
     *
     * Configures Spring Security.
     *
     * Responsibilities:
     * 1. Disable CSRF
     * 2. Make application Stateless
     * 3. Allow Authentication APIs
     * 4. Protect Remaining APIs
     * 5. Register JWT Filter
     * ============================================================
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .cors(Customizer.withDefaults())
                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // JWT applications should be Stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/test/public",
                                "/ws/**",
                                "/ws-endpoints/**"
                        ).permitAll()

                        // Protected APIs
                        .anyRequest().authenticated()
                )

                // Register JWT Filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}