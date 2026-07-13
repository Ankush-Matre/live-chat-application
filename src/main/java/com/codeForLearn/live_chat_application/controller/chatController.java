package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.model.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class chatController {

    @GetMapping("/")
    public String getChatMessage() {
        return "chat";
    }

    public ChatMessage receiveMessage(ChatMessage message){
        return message;
    }
}
