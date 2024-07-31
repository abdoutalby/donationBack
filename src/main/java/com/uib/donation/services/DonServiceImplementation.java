package com.uib.donation.services;

import com.uib.donation.models.Don;
import com.uib.donation.models.User;
import com.uib.donation.repositories.DonRepository;
import com.uib.donation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonServiceImplementation implements  DonService{

    private  final DonRepository donRepository;
    private  final UserRepository userRepository;


    @Override
    public ResponseEntity<?> createDon(Don don) {
        return ResponseEntity.ok(donRepository.save(don));
    }

    @Override
    public ResponseEntity<?> getDonsByCampaign(Long campaignId) {
        return ResponseEntity.ok(donRepository.findAllByCampagneId(campaignId));
    }

    @Override
    public ResponseEntity<?> getDonById(Long donId) {
        return ResponseEntity.ok(donRepository.findById(donId));
    }

    @Override
    public ResponseEntity<?> updateDon(Don don) {
        return ResponseEntity.ok(donRepository.save(don));
    }

    @Override
    public ResponseEntity<?> deleteDon(Long donId) {
        donRepository.deleteById(donId);
        return ResponseEntity.ok("deleted");
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(donRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getTotalDonsByUser(Long userId) {
        return  ResponseEntity.ok(donRepository.count());
    }


    @Override
    public ResponseEntity<?> getAllDonsByUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(donRepository.findAllByUser(user.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> getLastThree() {
        var dons = donRepository.findTop3ByOrderByDonDateDesc();
        return ResponseEntity.ok(dons);
    }
}
