package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * Returns all chat messages ordered by oldest first.
     */
    List<ChatMessage> findAllByOrderByTimeStampAsc();

}