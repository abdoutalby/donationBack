package com.uib.donation.repositories;

import com.uib.donation.models.Donataire;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DonataireRepo extends JpaRepository<Donataire, Long> {
}
