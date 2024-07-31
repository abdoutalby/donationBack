package com.uib.donation.services;

import com.uib.donation.models.User;
import com.uib.donation.utils.LoginRequest;
import com.uib.donation.utils.UserUpdateDao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<?> registerUser(User user) ;

    ResponseEntity<?> getUserById(Long id);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> updateUser(UserUpdateDao user);

    ResponseEntity<?> deleteUser(Long userId);

    ResponseEntity<?> countDonneur();

    ResponseEntity<?> countDonataire();

    ResponseEntity<?> countAdmin();

    ResponseEntity<?> totalDonneurs();

    ResponseEntity<?> totalDonataires();

    ResponseEntity<?> totalAdmins();

    ResponseEntity<?> changePassword(LoginRequest loginRequest);
}
