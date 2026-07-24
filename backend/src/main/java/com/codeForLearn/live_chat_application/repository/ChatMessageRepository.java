package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}