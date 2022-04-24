package com.example.ghospital.repositories;

import com.example.ghospital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepositorie extends JpaRepository<Consultation,Long> {
}
