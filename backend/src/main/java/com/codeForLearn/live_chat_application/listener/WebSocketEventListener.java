package com.codeForLearn.live_chat_application.listener;

import com.codeForLearn.live_chat_application.model.ChatMessage;
import com.codeForLearn.live_chat_application.model.MessageType;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener implements ApplicationListener<SessionDisconnectEvent> {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {

        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {

            ChatMessage leaveMessage = new ChatMessage();
            leaveMessage.setSender(username);
            leaveMessage.setType(MessageType.LEAVE);
            leaveMessage.setContent(username + " has left the chat");

            messagingTemplate.convertAndSend("/topic/messages", leaveMessage);
        }
    }
}