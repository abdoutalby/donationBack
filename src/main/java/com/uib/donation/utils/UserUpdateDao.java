package com.uib.donation.utils;

import com.uib.donation.models.Donataire;
import com.uib.donation.models.Donneur;
import com.uib.donation.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserUpdateDao {

    private Long id;

    private String name;

    private String email;

    private String username;

    @Enumerated(EnumType.STRING)
    private User.UserStatus status;

    private Donneur donneur;
    private Donataire donataire;

}
