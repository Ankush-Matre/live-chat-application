package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByRoomName(String roomName);

}