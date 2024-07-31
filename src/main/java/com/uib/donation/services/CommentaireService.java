package com.uib.donation.services;

import com.uib.donation.dao.CommentDao;
import com.uib.donation.models.Commentaire;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentaireService {
    ResponseEntity<?> createCommentaire(CommentDao commentaire);

    ResponseEntity<?> getCommentairesByCampaign(Long campaignId);

    ResponseEntity<?> getCommentaireById(Long commentaireId);

    ResponseEntity<?> updateCommentaire(Commentaire commentaire);

    ResponseEntity<?> deleteCommentaire(Long commentaireId);

    ResponseEntity<?> getLatestComments();

    ResponseEntity<?> findLatestByCampagneOwner(Long id);

    ResponseEntity<?> findLatestByUser(Long id);
}
