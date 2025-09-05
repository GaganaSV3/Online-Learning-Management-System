package com.learnpro.lms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learnpro.lms.model.Course;
public interface CourseRepository extends JpaRepository<Course, Long> {}
