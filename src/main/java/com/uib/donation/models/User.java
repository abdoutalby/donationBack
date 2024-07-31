package com.uib.donation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(unique = true)
        private String email;

        private String username;

        private String password;

        private int nbCampagne;
        private int nbGives;
        private int nbTakes;
        private Long totalDons;

        @Enumerated(EnumType.STRING)
        private UserType userType;

        @Enumerated(EnumType.STRING)
        private  UserStatus status;

        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Campagne> campagnes = new ArrayList<>();

        @OneToOne(cascade = CascadeType.ALL)
        private Donataire donataire;

        @OneToOne(cascade = CascadeType.ALL)
        private Donneur donneur;

        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Commentaire> commentaires = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Don> dons = new ArrayList<>();

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(userType.toString()));
        }
        @Override
        public boolean isAccountNonExpired() {
                return status.equals(UserStatus.ENABLED);
        }

        @Override
        public String getUsername(){
                return email;
        }
        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }
        public enum UserType {
            GIVER,
            TAKER,
            ADMIN
        }

        public enum UserStatus {
                ENABLED,
                PENDING,
                DISABLED
        }


}
