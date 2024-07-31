package com.uib.donation.controllers;


import com.uib.donation.models.User;
import com.uib.donation.services.AuthService;
import com.uib.donation.services.EmailService;
import com.uib.donation.utils.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private  final AuthService authService;
    private final EmailService emailService;

     @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return   authService.register(user);
    }

    @PostMapping("forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestBody LoginRequest loginRequest){
         return authService.forgetPassword(loginRequest);
    }


    @PostMapping("login")
    public  ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return  authService.login(loginRequest);
    }

}
