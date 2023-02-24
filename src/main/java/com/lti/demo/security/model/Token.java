package com.lti.demo.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Token {

	@Id
	@GeneratedValue
	public Integer id;

	@Column(unique = true)
	public String token;

	@Enumerated(EnumType.STRING)
	public TokenType tokenType = TokenType.BEARER;

	public boolean revoked;

	public boolean expired;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public AppUser appUser;

	public Token() {
		super();
	}

	public Token(String token, TokenType tokenType, boolean revoked, boolean expired, AppUser appUser) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.revoked = revoked;
		this.expired = expired;
		this.appUser = appUser;
	}

	public Token(Integer id, String token, TokenType tokenType, boolean revoked, boolean expired, AppUser appUser) {
		super();
		this.id = id;
		this.token = token;
		this.tokenType = tokenType;
		this.revoked = revoked;
		this.expired = expired;
		this.appUser = appUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public AppUser getUser() {
		return appUser;
	}

	public void setUser(AppUser appUser) {
		this.appUser = appUser;
	}

}
