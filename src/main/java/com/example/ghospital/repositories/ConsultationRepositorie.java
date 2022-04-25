package com.example.ghospital.repositories;

import com.example.ghospital.entities.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultationRepositorie extends JpaRepository<Consultation,Long> {
    Page<Consultation> findAllByDateConsultation(String date, Pageable pageable);
    Page<Consultation> findAll(Pageable pageable);
}
