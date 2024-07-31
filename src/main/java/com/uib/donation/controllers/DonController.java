package com.uib.donation.controllers;

import com.uib.donation.services.DonServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/dons/")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200"
})
public class DonController {

    private  final DonServiceImplementation donService ;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return donService.getAll();
    }

    @GetMapping("total/{id}")
    public ResponseEntity<?> getTotlaDons(@PathVariable("id")Long userId){
        return  donService.getTotalDonsByUser(userId);
    }

    @GetMapping("getAllUserDons/{idUser}")
    public ResponseEntity<?> getAllUserDons(@PathVariable("idUser")Long id ){
        return donService.getAllDonsByUser(id);
    }

    @GetMapping("getLastThree")
    public ResponseEntity<?> getLastThree(){
        return  donService.getLastThree();
    }

}


