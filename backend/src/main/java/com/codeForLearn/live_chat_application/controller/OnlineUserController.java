package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.service.online.OnlineUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class OnlineUserController {

    private final OnlineUserService onlineUserService;

    public OnlineUserController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @GetMapping("/api/online-users")
    public Set<String> getOnlineUsers() {

        return onlineUserService.getOnlineUsers();
    }
}