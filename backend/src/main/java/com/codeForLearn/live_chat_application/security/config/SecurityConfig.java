package com.codeForLearn.live_chat_application.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ===========================================================
 * Security Configuration
 *
 * This class configures Spring Security for our application.
 *
 * Current Features:
 * ✔ Disable CSRF
 * ✔ Disable Spring Default Login Page
 * ✔ Allow Authentication APIs
 * ✔ Allow WebSocket Connections
 *
 * Future:
 * ✔ JWT Authentication
 * ✔ Role Based Authorization
 * ===========================================================
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                /*
                 * Disable CSRF because we are building REST APIs.
                 */
                .csrf(csrf -> csrf.disable())

                /*
                 * Configure URL authorization.
                 */
                .authorizeHttpRequests(auth -> auth

                        /*
                         * Public Authentication APIs
                         */
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        /*
                         * WebSocket Endpoint
                         */
                        .requestMatchers("/ws-endpoints/**")
                        .permitAll()

                        /*
                         * Allow every other request for now.
                         * Later we will secure them using JWT.
                         */
                        .anyRequest()
                        .permitAll()
                )

                /*
                 * Disable Spring's Default Login Page
                 */
                .formLogin(form -> form.disable())

                /*
                 * Disable HTTP Basic Authentication
                 */
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}