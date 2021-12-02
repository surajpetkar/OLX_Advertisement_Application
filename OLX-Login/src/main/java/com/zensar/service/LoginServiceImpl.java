package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zensar.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginRepository repo;
	
	List<com.zensar.model.User> userList = new ArrayList<>();
	List<String> logoutTokenList = new ArrayList<>();
	int userId=0;
	
	@Override
	public ResponseEntity<com.zensar.model.User> create(com.zensar.model.User user) {
		repo.save(user);
		return new ResponseEntity<com.zensar.model.User>(user,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<com.zensar.model.User> read(String id) {
		Optional<com.zensar.model.User> users = repo.findById(id);
		com.zensar.model.User user = users.get();
		return new ResponseEntity<com.zensar.model.User>(user,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> auth() {
		return new ResponseEntity<String>("AK47KR",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> logout(String token) {
		logoutTokenList.add(token);
		return new ResponseEntity<String>("true",HttpStatus.OK);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.zensar.model.User user = repo.findByUserName(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User us = new User(user.getUserName(),user.getPassword(),authorities);
		return us;
	}

	@Override
	public boolean isLogout(String jwtToken) {
		if(logoutTokenList.contains(jwtToken)) {
			return true;
		}
		else
			return false;
	}

}
