package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO receiveMessage(@Payload ChatMessageDTO message) {
        return message;
    }

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