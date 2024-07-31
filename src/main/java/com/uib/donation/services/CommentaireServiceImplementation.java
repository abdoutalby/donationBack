package com.uib.donation.services;

import com.uib.donation.dao.CommentDao;
import com.uib.donation.models.Commentaire;
import com.uib.donation.repositories.CampagneRepository;
import com.uib.donation.repositories.CommentaireRepository;
import com.uib.donation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentaireServiceImplementation implements CommentaireService{

    private final CommentaireRepository commentaireRepository;
    private final UserRepository   userRepository;
    private final CampagneRepository campagneRepository;
    @Override
    public ResponseEntity<?> createCommentaire(CommentDao commentaire) {
        Commentaire newComment = new Commentaire();
        var user = userRepository.findById(commentaire.getUserId());
        if(user.isPresent()){
            newComment.setUser(user.get());
            var campagne   = campagneRepository.findById(commentaire.getPostId());
            if(campagne.isPresent()){
                var camp = campagne.get() ;
                camp.setNbComment(camp.getNbComment()+1);
                campagneRepository.save(camp);
                newComment.setCampagne(camp);
                newComment.setContenu(commentaire.getContent());
                newComment.setCommentDate(new Date());
            return ResponseEntity.ok(commentaireRepository.save(newComment));
            }else {
                return ResponseEntity.badRequest().build();
            }
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> getCommentairesByCampaign(Long campaignId) {

        return ResponseEntity.ok(commentaireRepository.findAllByCampagneId(campaignId));
    }

    @Override
    public ResponseEntity<?> getCommentaireById(Long commentaireId) {
        return  ResponseEntity.ok(commentaireRepository.findById(commentaireId));
    }

    @Override
    public ResponseEntity<?> updateCommentaire(Commentaire commentaire) {
        return  ResponseEntity.ok(commentaireRepository.save(commentaire));
    }

    @Override
    public ResponseEntity<?> deleteCommentaire(Long commentaireId) {
        return  ResponseEntity.ok(commentaireRepository.findById(commentaireId));
    }

    @Override
    public ResponseEntity<?> findLatestByCampagneOwner(Long id) {
       return ResponseEntity.ok( commentaireRepository.findAllByUserId(id));

    }

    @Override
    public ResponseEntity<?> findLatestByUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getLatestComments() {
        return ResponseEntity.ok(commentaireRepository.findAll());
    }
}
