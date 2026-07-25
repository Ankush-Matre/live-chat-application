package com.codeForLearn.live_chat_application.exception;

import com.codeForLearn.live_chat_application.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the entire application.
 *
 * Any exception thrown from controllers or services
 * will be handled here instead of crashing the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException.
     *
     * @param exception Custom exception
     * @return Standard API response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception) {

        ApiResponse response = ApiResponse.builder()
                .success(false)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any unexpected exception.
     *
     * @param exception Unexpected exception
     * @return Generic error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception exception) {

        ApiResponse response = ApiResponse.builder()
                .success(false)
                .message("Something went wrong: " + exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}