package com.codeForLearn.live_chat_application.security.service;

import com.codeForLearn.live_chat_application.entity.User;
import com.codeForLearn.live_chat_application.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ============================================================
 * Custom UserDetailsService
 * ------------------------------------------------------------
 * Loads user information from the database.
 *
 * Spring Security automatically calls this class whenever
 * it needs user details for authentication.
 * ============================================================
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repository used to fetch user information.
     */
    private final UserRepository userRepository;

    /**
     * Constructor Injection.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ============================================================
     * Load user by username.
     *
     * Spring Security calls this method automatically.
     * ============================================================
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        /*
         * Fetch user from database.
         */
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        /*
         * Convert our User entity into Spring Security User.
         */
        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),

                user.getPassword(),

                List.of(
                        new SimpleGrantedAuthority(
                                user.getRole().getName()
                        )
                )
        );
    }
}