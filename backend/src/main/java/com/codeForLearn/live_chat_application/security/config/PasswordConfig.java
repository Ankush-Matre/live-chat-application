package com.codeForLearn.live_chat_application.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ============================================================
 * Password Configuration
 * ------------------------------------------------------------
 * This configuration class provides a PasswordEncoder bean.
 *
 * BCryptPasswordEncoder is the industry standard algorithm
 * used for securely hashing passwords before storing them
 * inside the database.
 *
 * Never store plain text passwords.
 * ============================================================
 */
@Configuration
public class PasswordConfig {

    /**
     * Creates PasswordEncoder Bean.
     *
     * This bean can be injected anywhere in the project using:
     *
     * @Autowired
     * private PasswordEncoder passwordEncoder;
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}