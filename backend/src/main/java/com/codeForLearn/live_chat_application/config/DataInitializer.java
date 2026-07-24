package com.codeForLearn.live_chat_application.config;

import com.codeForLearn.live_chat_application.entity.ChatRoom;
import com.codeForLearn.live_chat_application.entity.Role;
import com.codeForLearn.live_chat_application.repository.ChatRoomRepository;
import com.codeForLearn.live_chat_application.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   ChatRoomRepository chatRoomRepository) {

        return args -> {

            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name("ADMIN")
                                .build()
                );
            }

            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name("USER")
                                .build()
                );
            }

            if (roleRepository.findByName("MODERATOR").isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name("MODERATOR")
                                .build()
                );
            }

            if (chatRoomRepository.findByRoomName("Global Chat").isEmpty()) {

                chatRoomRepository.save(
                        ChatRoom.builder()
                                .roomName("Global Chat")
                                .build()
                );
            }

            System.out.println("Database initialized successfully.");
        };
    }
}