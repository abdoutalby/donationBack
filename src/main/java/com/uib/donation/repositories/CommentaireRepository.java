package com.uib.donation.repositories;

import com.uib.donation.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire , Long> {

    List<Commentaire> findAllByCampagneId(Long id);

    @Query("SELECT c.id , c.contenu , c.commentDate FROM Commentaire c,Campagne cam WHERE cam.id = c.campagne.id AND cam.user.id = :id")
    List<Commentaire> findAllByUserId(@Param("id") Long id);
}
