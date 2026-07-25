package com.codeForLearn.live_chat_application.exception;

/**
 * Custom exception thrown whenever a requested
 * resource is not found in the database.
 *
 * Examples:
 * - User not found
 * - Chat room not found
 * - Message not found
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor to initialize exception with a custom message.
     *
     * @param message Error description
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}