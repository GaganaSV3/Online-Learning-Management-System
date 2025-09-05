package com.learnpro.lms.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourseIdOrderByModuleOrder(Long courseId);
}
