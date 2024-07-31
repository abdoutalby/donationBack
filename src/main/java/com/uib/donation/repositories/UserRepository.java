package com.uib.donation.repositories;


import com.uib.donation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Long countAllByuserType(User.UserType userType);

    List<User> findAllByuserType(User.UserType userType);
}
