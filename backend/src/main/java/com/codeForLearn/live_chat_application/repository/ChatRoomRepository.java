package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for ChatRoom entity.
 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    /**
     * Finds chat room by its name.
     */
    Optional<ChatRoom> findByRoomName(String roomName);

}