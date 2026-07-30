package com.codeForLearn.live_chat_application.service;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;

import java.util.List;

public interface ChatService {

    /**
     * Save chat message into database.
     */
    ChatMessageDTO saveMessage(ChatMessageDTO messageDTO);

    /**
     * Load complete chat history.
     */
    List<ChatMessageDTO> getChatHistory();
}