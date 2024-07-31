package com.uib.donation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Donataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // 'association' or 'entreprise'
    private String organizationName;
    private String country;
    private String activityField;
    private String logo;
    private String taxId;
    private String rib;
    private String description;
    private double totalRecived =0;
    @OneToOne
    private User user ;
}
