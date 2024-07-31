package com.uib.donation.controllers;

import com.uib.donation.dao.CampagneDao;
import com.uib.donation.dao.CampagneUpdateDao;
import com.uib.donation.dao.DonationDao;
import com.uib.donation.services.CampagneServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/campagne/")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200"
})
public class CampagneController {

    private  final CampagneServiceImplementation campagneService;

    @GetMapping("findAll")
    public ResponseEntity<?> findAllCampagne(){
        return  campagneService.getAll();
    }

    @GetMapping("count")
    public ResponseEntity<?> count(){
        return campagneService.count();
    }

    @GetMapping("almostFinished")
    public ResponseEntity<?> almostFinished(){
        return  campagneService.almostFinished();
    }

    @GetMapping("related/{cat}")
    public ResponseEntity<?> related(@PathVariable("cat") String related){
        return this.campagneService.related(related);
    }
    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return campagneService.getById(id);
    }

    @GetMapping("findAllByUserId/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id){
        return campagneService.getByUserId(id);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CampagneDao campagne){
        return campagneService.save(campagne);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id ,
                                    @RequestBody CampagneUpdateDao campagne){
        return campagneService.update(id ,campagne);
    }

    @PostMapping("donate")
    public ResponseEntity<?> donate(@RequestBody DonationDao don){
        return  campagneService.donate(don);
    }

    @GetMapping("donations/{id}")
    public ResponseEntity<?> getAllCampagneDonations(@PathVariable("id") Long id){
        return  campagneService.findAllDonationsByCampagne(id);
    }


}
