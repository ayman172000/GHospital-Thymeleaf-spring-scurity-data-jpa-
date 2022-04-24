package com.example.ghospital.repositories;


import com.example.ghospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepositorie extends JpaRepository<Patient,Long> {
    Patient findByNom(String name);
}
