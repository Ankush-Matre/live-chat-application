package com.codeForLearn.live_chat_application.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ============================================================
 * Security Utils
 * ------------------------------------------------------------
 * Utility class for retrieving details about the currently
 * authenticated user.
 * ============================================================
 */
public class SecurityUtils {

    /**
     * Private constructor.
     */
    private SecurityUtils() {
    }

    /**
     * ============================================================
     * Returns username of the currently logged-in user.
     * ============================================================
     */
    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName();
    }
}