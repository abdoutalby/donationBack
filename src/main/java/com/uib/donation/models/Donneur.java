package com.uib.donation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donneur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String avatar;
    private boolean isExistingClient;
    private Date birthDate;
    private String    country ;
    private double totalGives = 0 ;
    @OneToOne
    private User user ;
    }
