package com.codeForLearn.live_chat_application.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.codeForLearn.live_chat_application.dto.ChatMessageDTO;
import com.codeForLearn.live_chat_application.entity.ChatMessage;
import com.codeForLearn.live_chat_application.entity.ChatRoom;
import com.codeForLearn.live_chat_application.entity.User;
import com.codeForLearn.live_chat_application.mapper.ChatMessageMapper;
import com.codeForLearn.live_chat_application.repository.ChatMessageRepository;
import com.codeForLearn.live_chat_application.repository.ChatRoomRepository;
import com.codeForLearn.live_chat_application.repository.UserRepository;
import com.codeForLearn.live_chat_application.service.ChatService;
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

        // ============================================================
        // STEP 1 : Get sender username from incoming message
        // ============================================================
        String username = messageDTO.getSender();

        System.out.println("Current Username = " + username);

        // ============================================================
        // STEP 2 : Find existing user
        // ============================================================
        User sender = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username));

        System.out.println("Sender = " + sender.getUsername());

        // ============================================================
        // STEP 3 : Find General Chat Room
        // ============================================================
        ChatRoom room = chatRoomRepository
                .findByRoomName("General")
                .orElse(null);

        // ============================================================
        // STEP 4 : Create Room if it doesn't exist
        // ============================================================
        if (room == null) {

            room = ChatRoom.builder()
                    .roomName("General")
                    .build();

            room = chatRoomRepository.save(room);
        }

        // ============================================================
        // STEP 5 : Convert DTO to Entity
        // ============================================================
        ChatMessage chatMessage = chatMessageMapper.toEntity(messageDTO);

        // ============================================================
        // STEP 6 : Set Relationships
        // ============================================================
        chatMessage.setSender(sender);
        chatMessage.setChatRoom(room);

        // ============================================================
        // STEP 7 : Save Message
        // ============================================================
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // ============================================================
        // STEP 8 : Convert Entity back to DTO
        // ============================================================
        return chatMessageMapper.toDTO(savedMessage);
    }

    /**
     * ============================================================
     * Load Complete Chat History
     *
     * Retrieves all chat messages from the database,
     * converts them into DTOs and returns them.
     * ============================================================
     */
    @Override
    public List<ChatMessageDTO> getChatHistory() {

        // STEP 1 : Fetch all messages from database
        List<ChatMessage> messages =
                chatMessageRepository.findAllByOrderByTimeStampAsc();

        // STEP 2 : Convert Entity List into DTO List
        return messages.stream()
                .map(chatMessageMapper::toDTO)
                .collect(Collectors.toList());
    }
}