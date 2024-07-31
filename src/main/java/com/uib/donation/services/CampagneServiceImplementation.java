package com.uib.donation.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.uib.donation.dao.CampagneDao;
import com.uib.donation.dao.CampagneUpdateDao;
import com.uib.donation.dao.DonationDao;
import com.uib.donation.models.Campagne;
import com.uib.donation.models.Don;
import com.uib.donation.models.User;
import com.uib.donation.repositories.CampagneRepository;
import com.uib.donation.repositories.DonRepository;
import com.uib.donation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampagneServiceImplementation implements CampagneService{

    private final CampagneRepository campagneRepository;
    private final UserRepository   userRepository;
    private  final DonRepository donRepository;

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(campagneRepository.findAll());
    }

    @Override
    public ResponseEntity<?> save(CampagneDao campagne) {
        Optional<User> user = userRepository.findById(campagne.getUserId());
        if (user.isPresent()) {
            var campagneToSave = new Campagne();
            campagneToSave.setCampagneDate(campagne.getCampagneDate());
            campagneToSave.setDescription(campagne.getDescription());
            campagneToSave.setNom(campagne.getNom());
            campagneToSave.setImage(campagne.getImage());
            campagneToSave.setStatut(Campagne.CampagneStatut.PENDING);
            campagneToSave.setMontantCollecte(0);
            campagneToSave.setObjectifCollecte(campagne.getObjectifCollecte());
            campagneToSave.setUser(user.get());
            user.get().setNbCampagne(user.get().getNbCampagne()+1);
            userRepository.save(user.get());
            return ResponseEntity.ok(campagneRepository.save(campagneToSave));
        }else {
            return ResponseEntity.badRequest().build();
        }
        }

    @Override
    public ResponseEntity<?> update(Long id, CampagneUpdateDao campagne) {
        var campagneToUpdate = campagneRepository.findById(id);
        if(campagneToUpdate.isPresent()){
            var updated = campagneToUpdate.get() ;
            updated.setDescription(campagne.getDescription());
            updated.setNom(campagne.getNom());
            updated.setStatut(campagne.getStatut());
            updated.setObjectifCollecte(campagne.getObjectifCollecte());
            updated.setImage(campagne.getImage());
            return ResponseEntity.ok(campagneRepository.save(updated));
        }
        return ResponseEntity.badRequest().build();
        }

    @Override
    public ResponseEntity<?> delete(Long id) {
        campagneRepository.deleteById(id);
        JSONPObject response  = new JSONPObject("response" , "deleted");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return ResponseEntity.ok(campagneRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> donate(DonationDao donation) {
        Optional<Campagne> campagne = campagneRepository.findById(donation.getIdCampagne());
        if (campagne.isPresent()) {
            Optional<User> user = userRepository.findById(donation.getIdUser());
            if (user.isPresent()){
            Don don = new Don();
            don.setCampagne(campagne.get());
            don.setDonDate(donation.getDate());
            don.setMontant(donation.getAmount());
            don.setUser(user.get());
            var campagneToUpdate = campagne.get();
            campagneToUpdate.getDons().add(donRepository.save(don));
            campagneToUpdate.setMontantCollecte(campagneToUpdate.getMontantCollecte()+don.getMontant());
            User owner = campagneToUpdate.getUser();
            owner.getDonataire().setTotalRecived(owner.getDonataire().getTotalRecived()+don.getMontant());
            userRepository.save(owner);
            User upUser = user.get();
            upUser.getDonneur().setTotalGives(upUser.getDonneur ().getTotalGives()+don.getMontant());
            userRepository.save(upUser);
            campagneRepository.save(campagneToUpdate);
            return ResponseEntity.ok(campagneRepository.save(campagneToUpdate));
            }else {
                return ResponseEntity.badRequest().build();
            }
        }else {
         return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> findAllDonationsByCampagne(Long id) {
        Optional<Campagne> campagne = campagneRepository.findById(id);
        if (campagne.isPresent()){
            return ResponseEntity.ok(campagne.get().getDons());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> almostFinished() {
        List<Campagne> almostFinished = new ArrayList<>();
        for (Campagne campagne : campagneRepository.findAll()) {
            double completionRatio = campagne.getMontantCollecte() / campagne.getObjectifCollecte();
            if (completionRatio >= (1 - 0.1) && completionRatio < 1) {
                almostFinished.add(campagne);
            }
        }
        return ResponseEntity.ok( almostFinished);
    }

    @Override
    public ResponseEntity<?> getByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(campagneRepository.findAllByUserId(id));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(campagneRepository.count());
    }

    @Override
    public ResponseEntity<?> related(String related) {
        return ResponseEntity.ok( campagneRepository.findAllByCause(related));
    }
}
