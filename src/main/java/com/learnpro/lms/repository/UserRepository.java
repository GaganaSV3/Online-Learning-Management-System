package com.learnpro.lms.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnpro.lms.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
