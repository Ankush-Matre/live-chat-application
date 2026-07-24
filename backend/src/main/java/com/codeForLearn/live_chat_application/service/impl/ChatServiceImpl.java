package com.codeForLearn.live_chat_application.service.impl;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.mapper.ChatMessageMapper;
import com.codeForLearn.live_chat_application.repository.ChatMessageRepository;
import com.codeForLearn.live_chat_application.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    public ChatServiceImpl(ChatMessageRepository chatMessageRepository,
                           ChatMessageMapper chatMessageMapper) {

        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Override
    public ChatMessageDTO saveMessage(ChatMessageDTO messageDTO) {

        return messageDTO;
    }
}