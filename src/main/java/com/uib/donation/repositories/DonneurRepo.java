package com.uib.donation.repositories;

import com.uib.donation.models.Donneur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonneurRepo extends JpaRepository<Donneur , Long> {
}
