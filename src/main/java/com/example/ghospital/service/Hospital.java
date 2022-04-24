package com.example.ghospital.service;

import com.example.ghospital.entities.Consultation;
import com.example.ghospital.entities.Medcin;
import com.example.ghospital.entities.Patient;
import com.example.ghospital.entities.RendezVous;

public interface Hospital {
    Patient savePatient(Patient patient);
    Medcin saveMedcin(Medcin medcin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
