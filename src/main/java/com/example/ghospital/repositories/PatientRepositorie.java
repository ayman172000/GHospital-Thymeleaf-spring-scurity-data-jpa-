package com.example.ghospital.repositories;


import com.example.ghospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;


public interface PatientRepositorie extends JpaRepository<Patient,Long> {
    Patient findByNom(String name);
    public List<Patient> findByMalade(boolean m);// que les patient malade ou non selon la valeur de m
    Page<Patient> findByMalade(Boolean m, Pageable pageable);// que les patient malade ou non selon la valeur de m avec pagination(num page+size voir la couche resentation)
    //Page<Patient> findByMaladeAndScoreLessThan(Boolean m,int score,Pageable pageable);
    //Page<Patient> findByMaladeIsTrueAndScoreLessThan(int score,Pageable pageable);
    Page<Patient> findByDateNaissBetween(Date d1, Date d2, Pageable pageable);
    Page<Patient> findByNomContains(String kw,Pageable pageable);
    @Query("select p from Patient p where p.dateNaiss between :x and :y or p.nom like :z") //requette hql
    Page<Patient> chercherPatients (@Param("x") Date d1, @Param("y") Date d2, @Param("z") String nom, Pageable pageable);
}
