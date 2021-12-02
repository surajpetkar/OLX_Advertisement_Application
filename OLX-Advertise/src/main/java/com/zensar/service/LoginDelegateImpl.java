package com.zensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginDelegateImpl implements LoginDelegate{
	
	@Autowired
	LoginDelegate loginDelegate;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public boolean isvalidToken(String authToken) {
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", authToken);
		HttpEntity httpEntity = new HttpEntity(header);
		ResponseEntity<String> result = this.restTemplate.exchange("http://localhost:9000/token/validate", HttpMethod.GET, httpEntity, String.class); 
		return result.getBody().isEmpty();
	}

}
