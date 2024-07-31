package com.uib.donation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Campagne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    private String image;

    private String nom;

    private String cause ;
    private String description;

     private Date campagneDate;

    private double objectifCollecte;

    private double montantCollecte;

    @Enumerated(EnumType.STRING)
    private CampagneStatut statut;

    @ManyToOne
    private User user;

    private int nbComment = 0 ;

    @JsonIgnore
    @OneToMany(mappedBy = "campagne", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires = new ArrayList<>();


    @OneToMany(mappedBy = "campagne", cascade = CascadeType.ALL)
    private List<Don> dons = new ArrayList<>();

    public enum CampagneStatut {
        ACTIF, SUSPENDU, HIDDEN, REFUSED, PENDING
    }
}

