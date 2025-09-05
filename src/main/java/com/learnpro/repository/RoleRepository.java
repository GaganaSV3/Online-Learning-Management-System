package com.learnpro.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnpro.lms.model.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
