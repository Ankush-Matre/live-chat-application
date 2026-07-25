package com.codeForLearn.live_chat_application.repository;

import com.codeForLearn.live_chat_application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * ===========================================================
 * Repository for Role Entity
 *
 * Provides database operations related to Roles.
 *
 * Spring Data JPA automatically implements these methods.
 * ===========================================================
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * Example:
     * ROLE_USER
     * ROLE_ADMIN
     */
    Optional<Role> findByName(String name);

}