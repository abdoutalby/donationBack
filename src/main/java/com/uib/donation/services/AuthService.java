package com.uib.donation.services;


import com.uib.donation.config.JwtService;
import com.uib.donation.models.User;
import com.uib.donation.models.User.UserStatus;
import com.uib.donation.repositories.UserRepository;
import com.uib.donation.utils.AuthenticationResponse;
import com.uib.donation.utils.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private  final EmailService emailService;

    public ResponseEntity<?> register(User user) {
        Optional<User> test = userRepo.findByEmail(user.getEmail());
        if (test.isPresent()) {
            return ResponseEntity.badRequest().body("email exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getUserType().equals(User.UserType.ADMIN)){
            user.setStatus(User.UserStatus.ENABLED);
        } else {
            user.setStatus(User.UserStatus.PENDING);
        }
       User saved =  userRepo.save(user);
        var JwtToken = jwtService.generateToken(saved , saved.getId());
        return ResponseEntity.ok( AuthenticationResponse.builder()
                .token(JwtToken)
                .build());
    }




    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<User> Opuser =userRepo.findByEmail(loginRequest.getEmail());
         if (Opuser.isPresent()) {
            UserDetails user = Opuser.get();
            log.info(loginRequest.getEmail());
            log.info(loginRequest.getPassword());
            log.info(user.toString());
            if (Opuser.get().getStatus().equals(UserStatus.PENDING)) {
                return ResponseEntity.status(400).body("user is disabled");
            } 
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            log.info("after the authentication ");
            var JwtToken = jwtService.generateToken(user , Opuser.get().getId());
            log.info(JwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(JwtToken)
                    .build());
        }
    
        return ResponseEntity.status(403).build();
    }

    public ResponseEntity<?> forgetPassword(LoginRequest loginRequest) {
    Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
    if(user.isPresent()){
        String password = generateRandomPassword();
        User newUser = user.get();
        newUser.setPassword(passwordEncoder.encode(password));
        emailService.sendSimpleMessage(loginRequest.getEmail(), "new Password" ,
                "as you requested a new password  \n" +
                        "here is your new password "+password);
        return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
    }


    public static String generateRandomPassword() {
        StringBuilder passwordBuilder = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()-_=+[]{};':,.<>/?";
        int charactersLength = characters.length();
        Random random = new Random();
        for (int i = 0; i <10; i++) {
            int characterIndex = random.nextInt(charactersLength);
            passwordBuilder.append(characters.charAt(characterIndex));
        }
        return passwordBuilder.toString();
    }
}
