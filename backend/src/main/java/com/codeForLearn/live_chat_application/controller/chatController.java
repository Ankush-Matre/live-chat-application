package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.service.ChatService;
import com.codeForLearn.live_chat_application.service.online.OnlineUserService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final OnlineUserService onlineUserService;
    private final SimpMessagingTemplate messagingTemplate;


    /*
     * Constructor Injection
     */
    public ChatController(
            ChatService chatService,
            OnlineUserService onlineUserService,
            SimpMessagingTemplate messagingTemplate) {

        this.chatService = chatService;
        this.onlineUserService = onlineUserService;
        this.messagingTemplate = messagingTemplate;
    }


    /*
     * ============================================================
     * CHAT MESSAGE
     * ============================================================
     */

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO receiveMessage(
            @Payload ChatMessageDTO message) {

        return chatService.saveMessage(message);
    }


    /*
     * ============================================================
     * USER JOIN
     * ============================================================
     */

    @MessageMapping("/addUser")
    @SendTo("/topic/messages")
    public ChatMessageDTO addUser(
            @Payload ChatMessageDTO message,
            SimpMessageHeaderAccessor headerAccessor) {

        String username = message.getSender();

        /*
         * Store username inside WebSocket session.
         */
        headerAccessor
                .getSessionAttributes()
                .put("username", username);


        /*
         * Add user to online users.
         */
        onlineUserService.addUser(username);


        /*
         * Broadcast updated online users.
         */
        broadcastOnlineUsers();


        return message;
    }


    /*
     * ============================================================
     * USER LEAVE
     * ============================================================
     */

    @MessageMapping("/leaveUser")
    @SendTo("/topic/messages")
    public ChatMessageDTO leaveUser(
            @Payload ChatMessageDTO message) {

        String username = message.getSender();


        /*
         * Remove user from online users.
         */
        onlineUserService.removeUser(username);


        /*
         * Broadcast updated online users.
         */
        broadcastOnlineUsers();


        return message;
    }


    /*
     * ============================================================
     * BROADCAST ONLINE USERS
     * ============================================================
     */

    private void broadcastOnlineUsers() {

        messagingTemplate.convertAndSend(
                "/topic/online-users",
                onlineUserService.getOnlineUsers()
        );
    }
}