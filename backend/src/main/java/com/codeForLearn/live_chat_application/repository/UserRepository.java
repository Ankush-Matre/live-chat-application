package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * ============================================================
 * User Repository
 * ------------------------------------------------------------
 * Responsible for performing database operations on the User
 * entity.
 *
 * Spring Data JPA automatically generates implementations for
 * these methods based on their names.
 * ============================================================
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Check if username already exists.
     */
    boolean existsByUsername(String username);

    /**
     * Check if email already exists.
     */
    boolean existsByEmail(String email);

    /**
     * Find user by username.
     *
     * Used during Login Authentication.
     */
    Optional<User> findByUsername(String username);

}