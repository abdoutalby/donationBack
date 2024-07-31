package com.uib.donation.services;

import com.uib.donation.models.Don;
import org.springframework.http.ResponseEntity;

public interface DonService {
    ResponseEntity<?> createDon(Don don );

    ResponseEntity<?> getDonsByCampaign(Long campaignId);

    ResponseEntity<?> getDonById(Long donId);
    ResponseEntity<?> updateDon(Don don);
    ResponseEntity<?> deleteDon(Long donId);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getTotalDonsByUser(Long userId);

    ResponseEntity<?> getAllDonsByUser(Long id);

    ResponseEntity<?> getLastThree();
}
