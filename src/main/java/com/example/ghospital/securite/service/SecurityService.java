package com.example.ghospital.securite.service;

import com.example.ghospital.securite.entities.AppRole;
import com.example.ghospital.securite.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
}
