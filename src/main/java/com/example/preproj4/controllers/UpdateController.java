package com.example.preproj4.controllers;

import com.example.preproj4.security.Role;
import com.example.preproj4.security.User;
import com.example.preproj4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UpdateController {

    @Autowired
    private UserService userService;

    @GetMapping("/getCurrentUser")
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/adduser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastname,
            @RequestParam("age") int age,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(required = false, name = "ADMIN") String ADMIN,
            @RequestParam(required = false, name = "USER") String USER) {

        Set<Role> roles = new HashSet<>();

        if (ADMIN != null) {
            roles.add(new Role(2L, ADMIN));
        }
        if (USER != null) {
            roles.add(new Role(1L, USER));
        }
        if (ADMIN == null && USER == null) {
            roles.add(new Role(1L, USER));
        }

        User user = new User(name, lastname, age, email, password, roles);

        userService.add(user);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
