package com.codeForLearn.live_chat_application.controller;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.service.ChatService;
import com.codeForLearn.live_chat_application.service.online.OnlineUserService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final OnlineUserService onlineUserService;
    private final SimpMessagingTemplate messagingTemplate;


    /*
     * ============================================================
     * CONSTRUCTOR INJECTION
     * ============================================================
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
     * TYPING INDICATOR
     * ============================================================
     *
     * React sends:
     *
     * /app/typing
     *
     * Example:
     *
     * {
     *     "sender": "rahul",
     *     "typing": true
     * }
     *
     * Spring Boot broadcasts:
     *
     * /topic/typing
     *
     * ============================================================
     */

    @MessageMapping("/typing")
    public void typing(
            @Payload TypingMessage typingMessage) {

        messagingTemplate.convertAndSend(
                "/topic/typing",
                typingMessage
        );
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


    /*
     * ============================================================
     * TYPING MESSAGE CLASS
     * ============================================================
     */

    public static class TypingMessage {

        private String sender;

        private boolean typing;


        public TypingMessage() {
        }


        public String getSender() {
            return sender;
        }


        public void setSender(String sender) {
            this.sender = sender;
        }


        public boolean isTyping() {
            return typing;
        }


        public void setTyping(boolean typing) {
            this.typing = typing;
        }
    }
}