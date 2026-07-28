package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatService chatService;

    /**
     * Constructor Injection
     */
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Receive Chat Message
     *
     * Flow:
     * Client
     * ↓
     * Save into Database
     * ↓
     * Broadcast to all connected users
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO receiveMessage(
            @Payload ChatMessageDTO message) {

        return chatService.saveMessage(message);
    }

    /**
     * Add New User
     */
    @MessageMapping("/addUser")
    @SendTo("/topic/messages")
    public ChatMessageDTO addUser(
            @Payload ChatMessageDTO message,
            SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes()
                .put("username", message.getSender());

        return message;
    }
}