package com.learnpro.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnpro.lms.model.User;
import com.learnpro.lms.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService = new UserService();

    @GetMapping("/users")
    public List<User> users(){ return userService.findAll(); }

    @PostMapping("/users")
    public User createUser(@RequestBody User u, @RequestParam(required = false) String role){
        return userService.createUser(u, role);
    }

    @PostMapping("/users/{id}/roles")
    public void assignRole(@PathVariable Long id, @RequestParam String role){
        userService.assignRole(id, role);
    }
}
