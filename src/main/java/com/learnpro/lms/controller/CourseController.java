package com.learnpro.lms.controller;
import com.learnpro.lms.service.CourseService;
import com.learnpro.model.Course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService = new CourseService();

    @GetMapping
    public List<com.learnpro.lms.model.Course> list(){ return courseService.findAll(); }

    @PostMapping
    public Course create(@RequestBody CourseDto dto){ 
        Course c = ((Object) Course.builder()).title(dto.title).description(dto.description).category(dto.category).duration(dto.duration).level(dto.level).instructorName(dto.instructorName).build();
        return courseService.createCourse(c, dto.modules);
    }

    @PutMapping("/{id}")
    public com.learnpro.lms.model.Course update(@PathVariable Long id, @RequestBody CourseDto dto){
        Course patch = Course.builder().title(dto.title).description(dto.description).category(dto.category).duration(dto.duration).level(dto.level).instructorName(dto.instructorName).build();
        return courseService.updateCourse(id, patch, dto.modules);
    }

    public static class CourseDto {
        public String title; public String description; public String category; public String duration; public String level; public String instructorName; public java.util.List<String> modules;
    }
}
