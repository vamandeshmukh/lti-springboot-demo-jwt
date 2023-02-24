package com.lti.demo.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.demo.security.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  Optional<AppUser> findByEmail(String email);

}
