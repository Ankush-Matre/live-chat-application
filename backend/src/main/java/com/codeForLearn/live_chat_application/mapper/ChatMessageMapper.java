package com.codeForLearn.live_chat_application.mapper;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.entity.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {

    public ChatMessage toEntity(ChatMessageDTO dto) {

        ChatMessage entity = new ChatMessage();

        entity.setContent(dto.getContent());
        entity.setType(dto.getType());

        return entity;
    }

    public ChatMessageDTO toDTO(ChatMessage entity) {

        ChatMessageDTO dto = new ChatMessageDTO();

        dto.setContent(entity.getContent());
        dto.setType(entity.getType());

        if(entity.getSender()!=null){
            dto.setSender(entity.getSender().getUsername());
        }

        if(entity.getTimeStamp()!=null){
            dto.setTimeStamp(entity.getTimeStamp().toString());
        }

        return dto;
    }
}