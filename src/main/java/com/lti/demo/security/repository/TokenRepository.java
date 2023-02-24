package com.lti.demo.security.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.demo.security.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	List<Token> findByAppUser_Id(Integer id);

	Optional<Token> findByToken(String token);
}
