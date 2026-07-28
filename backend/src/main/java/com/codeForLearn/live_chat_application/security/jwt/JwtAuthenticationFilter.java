package com.codeForLearn.live_chat_application.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeForLearn.live_chat_application.security.service.CustomUserDetailsService;

import java.io.IOException;

/**
 * ============================================================
 * JWT Authentication Filter
 * ------------------------------------------------------------
 * Executes once for every incoming HTTP request.
 *
 * Responsibilities:
 * 1. Read Authorization Header
 * 2. Extract JWT Token
 * 3. Extract Username
 * 4. Load UserDetails
 * 5. Validate JWT
 * 6. Authenticate User
 * ============================================================
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * JWT Service
     */
    private final JwtService jwtService;

    /**
     * Loads user details from database.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor Injection.
     */
    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * ============================================================
     * Executes once for every request.
     * ============================================================
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        /*
         * Read Authorization Header
         */
        final String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + authHeader);

        /*
         * Skip if header is missing.
         */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        /*
         * Remove "Bearer " prefix.
         */
        String jwtToken = authHeader.substring(7);

        /*
         * Extract username.
         */
        String username = jwtService.extractUsername(jwtToken);

        System.out.println("Username from JWT: " + username);

        /*
         * Authenticate only if user is not already authenticated.
         */
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            /*
             * Load user details from database.
             */
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            System.out.println("Database User: " + userDetails.getUsername());
            System.out.println("Authorities: " + userDetails.getAuthorities());

            System.out.println(
                    "Token Valid: "
                            + jwtService.isTokenValid(jwtToken, userDetails.getUsername())
            );
            /*
             * Validate JWT.
             */
            if (jwtService.isTokenValid(jwtToken, userDetails.getUsername())) {

                /*
                 * Create Authentication Token.
                 */
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(

                                userDetails,

                                null,

                                userDetails.getAuthorities()
                        );

                /*
                 * Attach request details.
                 */
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                /*
                 * Store Authentication inside SecurityContext.
                 */
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);

                System.out.println("Authentication Stored Successfully");
            }
        }

        /*
         * Continue request.
         */
        filterChain.doFilter(request, response);
    }
}