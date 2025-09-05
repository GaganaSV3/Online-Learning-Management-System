package com.learnpro.lms.controller;
import com.learnpro.lms.service.CourseService;
import com.learnpro.model.Course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final CourseService courseService = new CourseService();

    @GetMapping("/courses")
    public List<com.learnpro.lms.model.Course> myCourses(){ return courseService.findAll(); } // frontend uses this to populate lists

    @PostMapping("/courses")
    public Course create(@RequestBody CreateDto dto){
        Course c = ((Object) Course.builder()).title(dto.title).description(dto.description).category(dto.category).duration(dto.duration).level(dto.level).instructorName(dto.instructorName).build();
        return courseService.createCourse(c, dto.modules);
    }

    @PutMapping("/courses/{id}")
    public Course update(@PathVariable Long id, @RequestBody CreateDto dto){
        Course patch = Course.builder().title(dto.title).description(dto.description).category(dto.category).duration(dto.duration).level(dto.level).instructorName(dto.instructorName).build();
        return courseService.updateCourse(id, patch, dto.modules);
    }

    public static class CreateDto { public String title; public String description; public String category; public String duration; public String level; public String instructorName; public java.util.List<String> modules; }
}
