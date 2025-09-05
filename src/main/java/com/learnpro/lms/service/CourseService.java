package com.learnpro.lms.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnpro.lms.model.Course;
import com.learnpro.lms.repository.CourseRepository;
import com.learnpro.lms.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepo = null;
    private final ModuleRepository moduleRepo = null;

    public List<Course> findAll(){ return courseRepo.findAll(); }
    public Course findById(Long id){ return courseRepo.findById(id).orElseThrow(); }

    @Transactional
    public Course createCourse(com.learnpro.lms.controller.Course c, List<String> moduleTitles){
        Course saved = courseRepo.save(c);
        if(moduleTitles != null){
            int i=1;
            for(String t: moduleTitles){
                Module m = Module.builder().title(t).moduleOrder(i++).course(saved).build();
                moduleRepo.save(m);
                saved.getModules().addAll(m);
            }
        }
        return saved;
    }

    @Transactional
    public Course updateCourse(Long id, com.learnpro.lms.controller.Course course, List<String> moduleTitles){
        Course c = courseRepo.findById(id).orElseThrow();
        c.setTitle(course.getTitle()!=null?course.getTitle():c.getTitle());
        c.setDescription(course.getDescription()!=null?course.getDescription():c.getDescription());
        c.setCategory(course.getCategory()!=null?course.getCategory():c.getCategory());
        c.setDuration(course.getDuration()!=null?course.getDuration():c.getDuration());
        c.setLevel(course.getLevel()!=null?course.getLevel():c.getLevel());
        c.setInstructorName(course.getInstructorName()!=null?course.getInstructorName():c.getInstructorName());
        courseRepo.save(c);
        if(moduleTitles!=null){
            // remove existing and add new for simplicity
            moduleRepo.findByCourseIdOrderByModuleOrder(id).forEach(moduleRepo::delete);
            int i=1;
            for(String t: moduleTitles){
                Module m = Module.builder().title(t).moduleOrder(i++).course(c).build();
                moduleRepo.save(m);
            }
        }
        return c;
    }
}
