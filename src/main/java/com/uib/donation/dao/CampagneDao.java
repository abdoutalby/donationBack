package com.uib.donation.dao;

import lombok.Data;

import java.util.Date;

@Data
public class CampagneDao {

    private String image;

    private String nom;

    private String description;

    private Date campagneDate;

    private double objectifCollecte;

    private  Long userId ;

}
