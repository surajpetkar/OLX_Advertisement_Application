package com.zensar.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.zensar.model.User;

public interface LoginService extends UserDetailsService{

	ResponseEntity<User> create(User user);
	ResponseEntity<User> read(String id);
	ResponseEntity<String> auth();
	ResponseEntity<String> logout(String token);
	public UserDetails loadUserByUsername(String username);
	boolean isLogout(String jwtToken);
}
