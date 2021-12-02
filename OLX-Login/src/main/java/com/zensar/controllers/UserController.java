package com.zensar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.model.User;
import com.zensar.security.dto.AuthenticationRequest;
import com.zensar.security.utils.JwtUtils;
import com.zensar.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {

	@Autowired
	LoginService service;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@PostMapping(value="/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authRequest){
		try {
			authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException(authRequest.getUsername());
		}
		String jwtToken = jwtUtils.generateToken(authRequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	@GetMapping(value="/token/validate")
	public ResponseEntity<Boolean> tokenValidate(@RequestHeader("Authorization") String jwtToken){
		System.out.println(jwtToken);
		jwtToken = jwtToken.substring(7,jwtToken.length());
		if(service.isLogout(jwtToken))
		{
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		else {
		System.out.println(jwtToken);
		String username = jwtUtils.extractUsername(jwtToken);
		UserDetails user = service.loadUserByUsername(username);
		System.out.println(user);
		boolean value = jwtUtils.validateToken(jwtToken, user);
		if(value) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Boolean>(false,HttpStatus.UNAUTHORIZED);
	}
	}
	
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Creating New User", notes="This service creates new User")
	public ResponseEntity<User> create(@RequestBody User user){
		return service.create(user);
	}
	
	@GetMapping("/user/{id}")
	@ApiOperation(value="Provide Existing User", notes="This service provides particular User")
	public ResponseEntity<User> read(@ApiParam(value="User Identifiers",required = true) @PathVariable String id){
		return service.read(id);
	}
	
	@PostMapping("/user/authenticate")
	@ApiOperation(value="Login User", notes="This is Login User Service")
	public ResponseEntity<String> auth(){
			return service.auth();
	}
	
	@DeleteMapping("/user/logout")
	@ApiOperation(value="Logout", notes="This is Logout Service")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
			return service.logout(token);
	}
}
