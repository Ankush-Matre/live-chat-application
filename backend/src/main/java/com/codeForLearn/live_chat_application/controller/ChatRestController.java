package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ============================================================
 * Chat REST Controller
 * ------------------------------------------------------------
 * Provides REST APIs related to chat.
 *
 * Current APIs:
 *
 * GET /api/messages
 *      Returns complete chat history.
 * ============================================================
 */
@RestController
@RequestMapping("/api")
public class ChatRestController {

    /**
     * Chat Service
     */
    private final ChatService chatService;

    /**
     * Constructor Injection
     */
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * ============================================================
     * Load Chat History
     *
     * URL:
     * GET /api/messages
     * ============================================================
     */
    @GetMapping("/messages")
    public List<ChatMessageDTO> getChatHistory() {

        return chatService.getChatHistory();
    }
}