package com.firststrpsspring.demo.controllers;

import com.firststrpsspring.demo.entity.Role;
import com.firststrpsspring.demo.entity.User;
import com.firststrpsspring.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo repo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = repo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message", "User exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        repo.save(user);
        return "redirect:/home";
    }
}
