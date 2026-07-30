package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final ChatService chatService;

    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/history")
    public List<ChatMessageDTO> getChatHistory() {

        return chatService.getChatHistory();
    }
}