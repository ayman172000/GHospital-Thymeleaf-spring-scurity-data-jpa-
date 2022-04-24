package com.example.ghospital.repositories;

import com.example.ghospital.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RendezVousRepositorie extends JpaRepository<RendezVous,String> {

    @Query("select r from RendezVous r where r.medcin.nom like :nm or r.patient.nom like :np")
    Page<RendezVous> search(@Param("nm") String nomMedcin,
                            @Param("np") String nomPatient,
                            Pageable pageable);

}
