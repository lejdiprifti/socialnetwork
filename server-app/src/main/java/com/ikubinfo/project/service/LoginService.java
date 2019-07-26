package com.ikubinfo.project.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import java.util.Date;

import java.util.UUID;

import com.ikubinfo.project.model.LoginRequest;
import com.ikubinfo.project.model.LoginResponse;
import com.ikubinfo.project.model.UserModel;
import com.ikubinfo.project.util.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginService {
	private UserService userService;

	public LoginService() {
		this.userService = new UserService();
	}

	public LoginResponse login(LoginRequest request) {

		UserModel loggedInUser = userService.getUserByEmail(request.getEmail());
		Claims customClaims = Jwts.claims();
		customClaims.put("email", loggedInUser.getEmail());
		customClaims.put("role", loggedInUser.getRole());
		String token = Jwts.builder().setClaims(customClaims)
				.setExpiration(
						Date.from(LocalDateTime.now().plus(1, ChronoUnit.HOURS).toInstant(ZoneOffset.ofHours(+1))))
				.setId(UUID.randomUUID().toString()) 
				.setIssuedAt(new Date()) 
				.setIssuer("ikubinfo") 
				.signWith(SignatureAlgorithm.HS256, Constants.JWT_KEY).compact();

		LoginResponse response = new LoginResponse();
		response.setJwt(token);
		response.setUser(loggedInUser);

		return response;

	}
}
