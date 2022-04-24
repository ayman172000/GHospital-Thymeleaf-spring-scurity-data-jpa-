package com.example.ghospital.service;

import com.example.ghospital.entities.Consultation;
import com.example.ghospital.entities.Medcin;
import com.example.ghospital.entities.Patient;
import com.example.ghospital.entities.RendezVous;
import com.example.ghospital.repositories.ConsultationRepositorie;
import com.example.ghospital.repositories.MedcinRepositorie;
import com.example.ghospital.repositories.PatientRepositorie;
import com.example.ghospital.repositories.RendezVousRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service//tous les class de la couche metier utilise cette notation
@Transactional
public class HospitalImpl implements Hospital {
    //@Autowired
    PatientRepositorie patientRepositorie;
    //@Autowired
    MedcinRepositorie medcinRepositorie;
    //@Autowired
    RendezVousRepositorie rendezVousRepositorie;
    //@Autowired
    ConsultationRepositorie consultationRepositorie;

    //injection des dependences avec constructeur
    public HospitalImpl(PatientRepositorie patientRepositorie,
                        MedcinRepositorie medcinRepositorie,
                        RendezVousRepositorie rendezVousRepositorie,
                        ConsultationRepositorie consultationRepositorie) {
        this.patientRepositorie = patientRepositorie;
        this.medcinRepositorie = medcinRepositorie;
        this.rendezVousRepositorie = rendezVousRepositorie;
        this.consultationRepositorie = consultationRepositorie;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepositorie.save(patient);
    }

    @Override
    public Medcin saveMedcin(Medcin medcin) {

        return medcinRepositorie.save(medcin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        //generer une chaine de caractere unique qui depend de la date system
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepositorie.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepositorie.save(consultation);
    }
}
