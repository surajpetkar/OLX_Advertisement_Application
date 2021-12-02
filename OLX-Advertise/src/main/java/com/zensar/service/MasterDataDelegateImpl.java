package com.zensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class MasterDataDelegateImpl implements MasterDataDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "STATUS-FROM-MASTER-DATA-SERVICE")
	public String getStatusById(String statusId) {
		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:9002/advertise/category",
				String.class);
		return response.getBody();
	}

}
