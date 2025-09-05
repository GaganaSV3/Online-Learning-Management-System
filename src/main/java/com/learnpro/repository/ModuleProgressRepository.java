package com.learnpro.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnpro.lms.model.ModuleProgress;
import com.learnpro.lms.model.User;
public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, Long> {
    Optional<ModuleProgress> findByUserAndModule(User user, Module module);
    List<ModuleProgress> findByUserIdAndModuleCourseId(Long userId, Long courseId);
}
