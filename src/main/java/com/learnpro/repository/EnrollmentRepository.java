package com.learnpro.repository;
import java.util.List;
import java.util.Optional;

import javax.lang.model.element.Element;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnpro.lms.model.Course;
import com.learnpro.lms.model.Enrollment;
import com.learnpro.lms.model.User;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserAndCourse(User user, Course course);
    List<Element> findByUserId(Long userId);
    List<Enrollment> findByCourseId(Long courseId);
}
