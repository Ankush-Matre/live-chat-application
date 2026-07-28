package com.codeForLearn.live_chat_application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ============================================================
 * Test Controller
 * ------------------------------------------------------------
 * This controller is used only for testing Spring Security
 * and JWT Authentication.
 *
 * APIs:
 *
 * 1. Public Endpoint
 * 2. Protected Endpoint
 *
 * This controller can be removed later after authentication
 * has been fully verified.
 * ============================================================
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * ============================================================
     * Public API
     *
     * No authentication required.
     *
     * URL:
     * GET /api/test/public
     * ============================================================
     */
    @GetMapping("/public")
    public String publicApi() {

        return "This is a Public API.";
    }

    /**
     * ============================================================
     * Protected API
     *
     * Requires a valid JWT Token.
     *
     * URL:
     * GET /api/test/hello
     * ============================================================
     */
    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        return "Hello "
                + authentication.getName()
                + ", JWT Authentication is working successfully!";
    }
}