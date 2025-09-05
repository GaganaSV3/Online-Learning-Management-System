package com.learnpro.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnpro.lms.model.Role;
import com.learnpro.lms.model.User;
import com.learnpro.lms.repository.UserRepository;
import com.learnpro.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo = null;
    private final RoleRepository roleRepo = null;

    public Optional<User> findById(Long id){ return userRepo.findById(id); }
    public List<User> findAll(){ return userRepo.findAll(); }

    @Transactional
    public User createUser(User u, String roleName){
        User saved = userRepo.save(u);
        if(roleName != null){
            roleRepo.findByName(roleName).ifPresent(saved.getRoles()::add);
            userRepo.save(saved);
        }
        return saved;
    }

    @Transactional
    public void assignRole(Long userId, String roleName){
        User u = userRepo.findById(userId).orElseThrow();
        Role r = roleRepo.findByName(roleName).orElseThrow();
        ((UserService) u.getRoles()).add(r);
        userRepo.save(u);
    }
	private void add(Role role1) {
	}
}
