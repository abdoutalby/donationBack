package com.uib.donation.controllers;

import com.uib.donation.models.User;
import com.uib.donation.services.UserServiceImplementation;
import com.uib.donation.utils.LoginRequest;
import com.uib.donation.utils.UserUpdateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/users/")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200"
})
public class UserController {

    private  final UserServiceImplementation userService;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody User user) {
        return  userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long id ){
        return userService.getUserById(id);
    }
    @PutMapping("changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody LoginRequest loginRequest) {
        return  userService.changePassword(loginRequest);
    }


    @PostMapping("update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id ,
            @RequestBody UserUpdateDao user) {
        return  userService.updateUser(user);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("getUserById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") long id){
        return  userService.getUserById(id);
    }

    @GetMapping("countDonneur")
    public ResponseEntity<?> countDonneur(){
        return  userService.countDonneur();
    }
    @GetMapping("totalDonneurs")
    public ResponseEntity<?> totalDonneurs(){
        return  userService.totalDonneurs();
    }


    @GetMapping("totalDonataires")
    public ResponseEntity<?> totalDonataires(){
        return  userService.totalDonataires();
    }

    @GetMapping("totalAdmins")
    public ResponseEntity<?> totalAdmins(){
        return  userService.totalAdmins();
    }

    @GetMapping("countDonatiare")
    public ResponseEntity<?> countDonatiare(){
        return  userService.countDonataire();
    }

    @GetMapping("countAdmin")
    public ResponseEntity<?> countAdmin(){
        return  userService.countAdmin();
    }
}
