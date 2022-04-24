package com.example.ghospital.securite.repositories;

import com.example.ghospital.securite.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String>
{
    AppUser findByUsername(String username);
}
