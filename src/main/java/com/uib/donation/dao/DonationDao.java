package com.uib.donation.dao;

import lombok.Data;

import java.util.Date;

@Data
public class DonationDao {
    private  double amount ;
    private  Long idCampagne ;
    private  Long idUser ;
    private Date date;
}
