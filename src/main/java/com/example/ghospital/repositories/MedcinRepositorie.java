package com.example.ghospital.repositories;

import com.example.ghospital.entities.Medcin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedcinRepositorie extends JpaRepository<Medcin,Long> {
    Medcin findByNom(String name);
}
