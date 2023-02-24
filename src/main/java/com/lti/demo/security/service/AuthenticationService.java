package com.lti.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.demo.security.model.AppUser;
import com.lti.demo.security.model.AuthenticationRequest;
import com.lti.demo.security.model.AuthenticationResponse;
import com.lti.demo.security.model.RegisterRequest;
import com.lti.demo.security.model.Role;
import com.lti.demo.security.model.Token;
import com.lti.demo.security.model.TokenType;
import com.lti.demo.security.repository.TokenRepository;
import com.lti.demo.security.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		AppUser appUser = new AppUser(request.getFirstname(), request.getLastname(), request.getEmail(),
				passwordEncoder.encode(request.getPassword()), Role.USER);

		AppUser savedUser = repository.save(appUser);
		String jwtToken = jwtService.generateToken(appUser);
		saveUserToken(savedUser, jwtToken);
		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		AppUser appUser = repository.findByEmail(request.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(appUser);
		revokeAllUserTokens(appUser);
		saveUserToken(appUser, jwtToken);
		return new AuthenticationResponse(jwtToken);
	}

	private void saveUserToken(AppUser appUser, String jwtToken) {
		Token token = new Token(jwtToken, TokenType.BEARER, false, false, appUser);
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(AppUser appUser) {
		var validUserTokens = tokenRepository.findByAppUser_Id(appUser.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
}
