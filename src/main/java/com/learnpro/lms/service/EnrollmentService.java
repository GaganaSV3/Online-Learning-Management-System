package com.learnpro.lms.service;
import java.util.List;

import javax.lang.model.element.Element;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnpro.lms.model.Course;
import com.learnpro.lms.model.Enrollment;
import com.learnpro.lms.model.EnrollmentStatus;
import com.learnpro.lms.model.ModuleProgress;
import com.learnpro.lms.model.ProgressStatus;
import com.learnpro.lms.model.User;
import com.learnpro.lms.repository.CourseRepository;
import com.learnpro.lms.repository.ModuleRepository;
import com.learnpro.lms.repository.UserRepository;
import com.learnpro.repository.EnrollmentRepository;
import com.learnpro.repository.ModuleProgressRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class EnrollmentService {
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final ModuleRepository moduleRepo;
    private final ModuleProgressRepository moduleProgressRepo;

    @Transactional
    public Enrollment enrollStudent(Long userId, Long courseId){
        User user = userRepo.findById(userId).orElseThrow();
        boolean isStudent = user.getRoles().stream().anyMatch(r->"STUDENT".equals(r.getName()));
        if(!isStudent) throw new RuntimeException("Only STUDENT role can enroll");
        Course course = courseRepo.findById(courseId).orElseThrow();
        enrollmentRepo.findByUserAndCourse(user, course).ifPresent(e -> { throw new RuntimeException("Already enrolled"); });
        Enrollment e = Enrollment.builder().user(user).course(course).progressPercent(0.0).status(EnrollmentStatus.ENROLLED).build();
        Enrollment saved = enrollmentRepo.save(e);
        moduleRepo.findByCourseIdOrderByModuleOrder(courseId).forEach(m -> {
            try{
                ModuleProgress mp = ModuleProgress.builder().user(user).module(m).status(ProgressStatus.NOT_STARTED).build();
                moduleProgressRepo.save(mp);
            }catch(Exception ex){}
        });
        return saved;
    }

    public List<Element> getUserEnrollments(Long userId){ return enrollmentRepo.findByUserId(userId); }
    public List<Enrollment> getCourseEnrollments(Long courseId){ return enrollmentRepo.findByCourseId(courseId); }

    @Transactional
    public void markModuleCompleted(Long userId, Long moduleId){
        User user = userRepo.findById(userId).orElseThrow();
        Module mod = moduleRepo.findById(moduleId).orElseThrow();
        ModuleProgress mp = moduleProgressRepo.findByUserAndModule(user, mod).orElse(ModuleProgress.builder().user(user).module(mod).build());
        mp.setStatus(ProgressStatus.COMPLETED); mp.setCompletedAt(java.time.Instant.now());
        moduleProgressRepo.save(mp);
        // recompute progress
        Long courseId = mod.getCourse().getId();
        long total = moduleRepo.findByCourseIdOrderByModuleOrder(courseId).size();
        long done = moduleProgressRepo.findByUserIdAndModuleCourseId(userId, courseId).stream().filter(x->x.getStatus()==ProgressStatus.COMPLETED).count();
        double percent = total==0?0.0:Math.round((done*10000.0/total))/100.0;
        Enrollment e = enrollmentRepo.findByUserAndCourse(user, mod.getCourse()).orElseThrow();
        e.setProgressPercent(percent);
        if(percent>=100.0) e.setStatus(EnrollmentStatus.COMPLETED);
        enrollmentRepo.save(e);
    }
}
