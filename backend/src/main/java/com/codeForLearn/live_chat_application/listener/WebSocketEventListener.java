package com.codeForLearn.live_chat_application.listener;

import com.codeForLearn.live_chat_application.service.online.OnlineUserService;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final OnlineUserService onlineUserService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(
            OnlineUserService onlineUserService,
            SimpMessagingTemplate messagingTemplate) {

        this.onlineUserService = onlineUserService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * ============================================================
     * Detect WebSocket Disconnect
     * ============================================================
     */
    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        /*
         * Get username stored inside WebSocket session.
         */
        String username = null;

        if (accessor.getSessionAttributes() != null) {

            username = (String) accessor
                    .getSessionAttributes()
                    .get("username");
        }

        /*
         * Remove user if username exists.
         */
        if (username != null) {

            onlineUserService.removeUser(username);

            /*
             * Broadcast updated online users.
             */
            messagingTemplate.convertAndSend(
                    "/topic/online-users",
                    onlineUserService.getOnlineUsers()
            );

            System.out.println(
                    "WebSocket Disconnected : " + username
            );

            System.out.println(
                    "Online Users : "
                            + onlineUserService.getOnlineUsers()
            );

            System.out.println(
                    "Online Count : "
                            + onlineUserService.getOnlineUserCount()
            );
        }
    }
}