package com.uib.donation.repositories;

import com.uib.donation.models.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne  , Long> {
    List<Campagne> findAllByUserId(Long id);
    List<Campagne> findAllByCause(String cause);
}
