package com.example.preproj4.dao;

import com.example.preproj4.security.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    void add(Role role);
    void delete(Role role);
}
