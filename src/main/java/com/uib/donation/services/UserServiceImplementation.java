package com.uib.donation.services;

import com.uib.donation.models.User;
import com.uib.donation.repositories.UserRepository;
import com.uib.donation.utils.LoginRequest;
import com.uib.donation.utils.UserUpdateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private  final UserRepository   userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerUser(User user) {
        Optional<User> isExist = userRepository.findByEmail(user.getEmail());
        if(isExist.isEmpty()) {
            user.setStatus(User.UserStatus.PENDING);
            return  ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.badRequest().body("email already exists");
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> updateUser(UserUpdateDao user) {
        Optional<User> up = userRepository.findById(user.getId());
        if(up.isPresent()){
            User u = up.get();
            u.setEmail(user.getEmail());
            u.setStatus(user.getStatus());
            u.setUsername(user.getUsername());
            u.setName(user.getName());
            u.setDonneur(user.getDonneur());
            u.setDonataire(user.getDonataire());
            return  ResponseEntity.ok(userRepository.save(u));
        }
        return  ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return  ResponseEntity.ok("deleted");
    }

    @Override
    public ResponseEntity<?> countDonneur() {
        return ResponseEntity.ok(userRepository.countAllByuserType(User.UserType.GIVER));
    }

    @Override
    public ResponseEntity<?> totalDonneurs() {
        return ResponseEntity.ok(userRepository.findAllByuserType(User.UserType.GIVER));
    }

    @Override
    public ResponseEntity<?> totalDonataires() {
        return ResponseEntity.ok(userRepository.findAllByuserType(User.UserType.TAKER));
    }

    @Override
    public ResponseEntity<?> totalAdmins() {
        return ResponseEntity.ok(userRepository.findAllByuserType(User.UserType.ADMIN));
    }

    @Override
    public ResponseEntity<?> changePassword(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findById(Long.valueOf( loginRequest.getEmail()));
        if(user.isPresent()){
            User upUser = user.get();
            upUser.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            userRepository.save(upUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> countDonataire() {
        return ResponseEntity.ok(userRepository.countAllByuserType(User.UserType.TAKER));
    }

    @Override
    public ResponseEntity<?> countAdmin() {
        return ResponseEntity.ok(userRepository.countAllByuserType(User.UserType.ADMIN));
    }

}
