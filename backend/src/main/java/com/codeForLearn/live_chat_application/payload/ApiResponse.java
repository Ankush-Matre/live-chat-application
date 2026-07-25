package com.codeForLearn.live_chat_application.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ===========================================================
 * Generic API Response
 *
 * Used by every REST API in the application.
 * ===========================================================
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    /**
     * Indicates whether request was successful.
     */
    private boolean success;

    /**
     * Response message.
     */
    private String message;

    /**
     * Response data.
     */
    private T data;
}