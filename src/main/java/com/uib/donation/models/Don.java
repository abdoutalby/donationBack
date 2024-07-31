package com.uib.donation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Don {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private double montant;

        @Temporal(TemporalType.DATE)
        private Date donDate;

        @ManyToOne
        private User user;

        @ManyToOne
        private Campagne campagne;

}
