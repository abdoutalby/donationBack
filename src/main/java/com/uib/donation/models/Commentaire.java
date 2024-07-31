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
public class Commentaire {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String contenu;

        @Temporal(TemporalType.DATE)
        private Date commentDate;

        @ManyToOne
        private User user;

        @ManyToOne
        private Campagne campagne;

}
