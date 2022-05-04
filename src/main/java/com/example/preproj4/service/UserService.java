package com.example.preproj4.service;

import com.example.preproj4.security.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add(User user);
    void delete(User user);
    void update(User user);
    User getById(long id);
    User getByName(String name);
}

