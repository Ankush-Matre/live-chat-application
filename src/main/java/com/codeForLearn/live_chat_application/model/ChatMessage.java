package com.codeForLearn.live_chat_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String sender;
    private String content;
    private MessageType messageType;

}
