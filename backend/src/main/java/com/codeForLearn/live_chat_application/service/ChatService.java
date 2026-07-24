package com.codeForLearn.live_chat_application.service;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;

public interface ChatService {

    ChatMessageDTO saveMessage(ChatMessageDTO messageDTO);

}