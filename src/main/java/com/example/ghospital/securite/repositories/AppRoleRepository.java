package com.example.ghospital.securite.repositories;

import com.example.ghospital.securite.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long>
{
    AppRole findByRolename(String roleName);
}
