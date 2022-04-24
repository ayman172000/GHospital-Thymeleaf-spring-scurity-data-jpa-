package com.example.ghospital;

import com.example.ghospital.entities.*;
import com.example.ghospital.repositories.ConsultationRepositorie;
import com.example.ghospital.repositories.MedcinRepositorie;
import com.example.ghospital.repositories.PatientRepositorie;
import com.example.ghospital.repositories.RendezVousRepositorie;
import com.example.ghospital.securite.service.SecurityService;
import com.example.ghospital.service.Hospital;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class GHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GHospitalApplication.class, args);
    }
    @Bean //au demarage cree moi un objet de type et tu le place dans ton contectext et si vous voulez le reeutiliser utiliser @autowired
    PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

    //@Bean
        //une methode qui contient l'annotation Bean il vas s'executer au demmarage
    CommandLineRunner start(
            PatientRepositorie patientRepositorie,
            RendezVousRepositorie rendezVousRepositorie,
            MedcinRepositorie medcinRepositorie,
            ConsultationRepositorie consultationRepositorie,
            Hospital hospitalService)
    {
        return args -> {
            //ajout de trois patients
            Stream.of("ayman","mouad","douae").forEach(name->{
                Patient patient=new Patient();
                patient.setNom(name);
                patient.setDateNaiss(new Date());
                patient.setMalade(false);
                //patientRepositorie.save(patient);
                hospitalService.savePatient(patient);
            });
            //ajout de trois medcins
            Stream.of("hamid","rabii","ismail").forEach(name->{
                Medcin medcin=new Medcin();
                medcin.setNom(name);
                medcin.setMail(name+"@gmail.com");
                medcin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                //medcinRepositorie.save(medcin);
                hospitalService.saveMedcin(medcin);

            });
            //Patient patient=patientRepositorie.findById(1L).orElse(null);
            Patient patient1=patientRepositorie.findByNom("ayman");
            Medcin medcin =medcinRepositorie.findByNom("hamid");
            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRdv.PENDING);
            rendezVous.setMedcin(medcin);
            rendezVous.setPatient(patient1);
            //rendezVousRepositorie.save(rendezVous);
            hospitalService.saveRendezVous(rendezVous);

            //RendezVous rendezVous1=rendezVousRepositorie.findById(1l).orElse(null);
            RendezVous rendezVous1=rendezVousRepositorie.findAll().get(0);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("rapport de la consultation");
            //consultationRepositorie.save(consultation);
            hospitalService.saveConsultation(consultation);
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("ayman","1234","1234");
            securityService.saveNewUser("nabil","1234","1234");
            securityService.saveNewUser("mouad","1234","1234");
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");
            securityService.addRoleToUser("ayman","ADMIN");
            securityService.addRoleToUser("ayman","USER");
            securityService.addRoleToUser("nabil","USER");
            securityService.addRoleToUser("mouad","USER");
        };
    }

}
