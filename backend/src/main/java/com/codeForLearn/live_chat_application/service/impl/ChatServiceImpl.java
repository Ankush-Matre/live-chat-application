package com.codeForLearn.live_chat_application.service.impl;

import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.entity.ChatMessage;
import com.codeForLearn.live_chat_application.entity.ChatRoom;
import com.codeForLearn.live_chat_application.entity.User;
import com.codeForLearn.live_chat_application.mapper.ChatMessageMapper;
import com.codeForLearn.live_chat_application.repository.ChatMessageRepository;
import com.codeForLearn.live_chat_application.repository.ChatRoomRepository;
import com.codeForLearn.live_chat_application.repository.UserRepository;
import com.codeForLearn.live_chat_application.service.ChatService;
import com.codeForLearn.live_chat_application.security.util.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * Constructor Injection
     *
     * Spring automatically injects all required repositories
     * and mapper into this service.
     */
    public ChatServiceImpl(
            ChatMessageRepository chatMessageRepository,
            ChatMessageMapper chatMessageMapper,
            UserRepository userRepository,
            ChatRoomRepository chatRoomRepository) {

        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageMapper = chatMessageMapper;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public ChatMessageDTO saveMessage(ChatMessageDTO messageDTO) {



         // step 1 : Find existing user
        String username = SecurityUtils.getCurrentUsername();
        System.out.println("Current Username = " + username);
        User sender = userRepository
                .findByUsername(username)
                .orElse(null);

        System.out.println("Sender = " + sender);
         //STEP 2 : Create user if not found
        if (sender == null) {

            sender = User.builder()
                    .username(username)
                    .build();

            sender = userRepository.save(sender);
        }


        //Step 3 : find general chat room
        ChatRoom room = chatRoomRepository
                .findByRoomName("General")
                .orElse(null);

        //STEP 4 : Create room if it doesn't exist
        if (room == null) {

            room = ChatRoom.builder()
                    .roomName("General")
                    .build();

            room = chatRoomRepository.save(room);
        }

        // STEP 5 : Convert DTO into Entity
        ChatMessage chatMessage = chatMessageMapper.toEntity(messageDTO);

        // STEP 6 : Set Entity Relationships
        chatMessage.setSender(sender);
        chatMessage.setChatRoom(room);

        // STEP 7 : Save into Database
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // STEP 8 : Convert Entity back into DTO
        return chatMessageMapper.toDTO(savedMessage);
    }
}