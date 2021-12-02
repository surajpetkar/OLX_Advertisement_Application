package com.zensar.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zensar.model.Advertise;

public interface AdvertiseService {

	ResponseEntity<Advertise> create(String authToken, Advertise ad);
	ResponseEntity<Advertise> update(String authToken, Advertise ad,String id);
	List<Advertise> getAll(String authToken);
	ResponseEntity<Advertise> getById(String authToken, String id);
	ResponseEntity<Boolean> deleteById(String authToken, String id);
	ResponseEntity<Advertise> searchCritetia(String fc);
	ResponseEntity<Advertise> getAdById(String authToken, String id);
	
}
