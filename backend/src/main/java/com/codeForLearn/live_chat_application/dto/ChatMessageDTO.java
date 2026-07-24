package com.codeForLearn.live_chat_application.dto;

import com.codeForLearn.live_chat_application.model.MessageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDTO {

    private String sender;

    private String content;

    private MessageType type;

    private String timeStamp;
}