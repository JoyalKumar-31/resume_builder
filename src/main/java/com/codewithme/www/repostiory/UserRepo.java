package com.codewithme.www.repostiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithme.www.model.user;

@Repository
public interface UserRepo extends JpaRepository<user,Integer> {
	Optional<user> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional <user> findByVerificationToken(String verificationToken);
}
