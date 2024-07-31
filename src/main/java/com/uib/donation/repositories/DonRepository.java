package com.uib.donation.repositories;

import com.uib.donation.models.Don;
import com.uib.donation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DonRepository extends JpaRepository<Don , Long > {

    List<Don> findAllByUser(User user);
    List<Don> findAllByCampagneId(Long id );

    List<Don> findTop3ByOrderByDonDateDesc();
}
