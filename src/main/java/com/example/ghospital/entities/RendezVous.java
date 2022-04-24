package com.example.ghospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date date;
    @Enumerated(EnumType.STRING)//pour utiliser la valeur string de l'enumeration
    private StatusRdv status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;
    @ManyToOne
    private  Medcin medcin;
    @OneToOne(mappedBy = "rendezVous")
    private  Consultation consultation;
}
