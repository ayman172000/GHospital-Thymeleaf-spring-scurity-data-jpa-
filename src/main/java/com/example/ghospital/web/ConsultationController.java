package com.example.ghospital.web;


import com.example.ghospital.entities.Consultation;
import com.example.ghospital.entities.RendezVous;
import com.example.ghospital.repositories.ConsultationRepositorie;
import com.example.ghospital.repositories.RendezVousRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@AllArgsConstructor
public class ConsultationController {
    private ConsultationRepositorie consultationRepositorie;
    private RendezVousRepositorie rendezVousRepositorie;

    @GetMapping(path = "/user/Consultation")
    public  String RendezVous(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size/*,
            @RequestParam(name = "KeyWord",defaultValue = "") String KeyWord*/) {
        Page<Consultation> PageConsultations=consultationRepositorie.findAll(PageRequest.of(page,size));
        //Page<Patient> PagePatients=patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listConsultation", PageConsultations.getContent());
        model.addAttribute("pages", new int[PageConsultations.getTotalPages()]);
        model.addAttribute("currentPage", page);
        //model.addAttribute("KeyWord", KeyWord);
        return "Consultation";//une vue basée sur lae moteur de recherche tymelift
    }

}
