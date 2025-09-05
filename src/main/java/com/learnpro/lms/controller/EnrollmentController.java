package com.learnpro.lms.controller;
import com.learnpro.lms.service.EnrollmentService;
import com.learnpro.model.Enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.lang.model.element.Element;
@RestController
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService = new EnrollmentService();

    // POST /api/enroll  { "courseId": 123 }  header X-USER-ID
    @PostMapping("/api/enroll")
    public Element enroll(@RequestHeader("X-USER-ID") Long userId, @RequestBody EnrollReq req){
        return (Element) enrollmentService.enrollStudent(userId, req.courseId);
    }

    @GetMapping("/api/me/enrollments")
    public List<Element> myEnrollments(@RequestHeader("X-USER-ID") Long userId){
        return enrollmentService.getUserEnrollments(userId);
    }

    // overall progress per user
    @GetMapping("/api/me/progress")
    public ProgressResp myProgress(@RequestHeader("X-USER-ID") Long userId){
        List<Element> list = enrollmentService.getUserEnrollments(userId);
        double overall = 0; int n = list.size();
        double completed = 0;
        for(var e: list){ overall += e.getProgressPercent(); if(e.getProgressPercent()>=100.0) completed++; }
        return new ProgressResp(n, Math.round((n==0?0:overall/n)*100.0)/100.0, (int)completed, list);
    }

    @PostMapping("/api/me/modules/{moduleId}/complete")
    public void completeModule(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long moduleId){
        enrollmentService.markModuleCompleted(userId, moduleId);
    }

    public static class EnrollReq { public Long courseId; }
    public static class ProgressResp { public int enrolledCount; public double overallPercent; public int completedCount; public java.util.List<Element> enrollments;
        public ProgressResp(int enrolledCount, double overallPercent, int completedCount, java.util.List<Element> enrollments){ this.enrolledCount=enrolledCount; this.overallPercent=overallPercent; this.completedCount=completedCount; this.enrollments=enrollments; }
    }
}
