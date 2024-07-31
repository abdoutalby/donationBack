package com.uib.donation.dao;

import com.uib.donation.models.Campagne;
import lombok.Data;

@Data
public class CampagneUpdateDao {

    private String image;

    private String nom;

    private String description;

    private double objectifCollecte;

    private Campagne.CampagneStatut Statut;

}
