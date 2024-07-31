package com.uib.donation.services;

import com.uib.donation.dao.CampagneDao;
import com.uib.donation.dao.CampagneUpdateDao;
import com.uib.donation.dao.DonationDao;
import com.uib.donation.models.Campagne;
import com.uib.donation.models.Don;
import org.springframework.http.ResponseEntity;

public interface CampagneService {
    ResponseEntity<?> getAll();
    ResponseEntity<?> save(CampagneDao campagne);
    ResponseEntity<?> update(Long id , CampagneUpdateDao campagne);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> donate(DonationDao don);

    ResponseEntity<?> findAllDonationsByCampagne(Long id);

    ResponseEntity<?> almostFinished();

    ResponseEntity<?> getByUserId(Long id);

    ResponseEntity<?> count();

    ResponseEntity<?> related(String related);
}
