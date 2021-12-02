package com.zensar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.model.Advertise;
import com.zensar.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AdvertiseController {

	@Autowired
	AdvertiseService service;

	@PostMapping(value = "/advertise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Creating New Ads", notes = "This service creates new Ads")
	public ResponseEntity<Advertise> create(@RequestHeader("auth-token") String authToken, @RequestBody Advertise ad) {
		return service.create(authToken, ad);
	}

	@PutMapping("/advertise/{id}")
	@ApiOperation(value = "Update Ads", notes = "This service update exisiting Ads")
	public ResponseEntity<Advertise> update(@RequestHeader("auth-token") String authToken, @RequestBody Advertise ad, @PathVariable("id") String id) {
		return service.update(authToken, ad, id);
	}

	@GetMapping("/user/advertise")
	@ApiOperation(value = "Provide All Ads", notes = "Provide All Existing Ads")
	public List<Advertise> getAll(@RequestHeader("auth-token") String authToken) {
		return service.getAll(authToken);
	}

	@GetMapping("/user/advertise/{id}")
	@ApiOperation(value = "Provide Ads by Id", notes = "Provide existing Ads by Id")
	public ResponseEntity<Advertise> getById(@RequestHeader("auth-token") String authToken, 
			@ApiParam(value = "Ads Identifiers", required = true) @PathVariable("id") String id) {
		return service.getById(authToken, id);
	}
	
	@DeleteMapping("/user/advertise/{id}")
	@ApiOperation(value = "Delete Ads by Id", notes = "Delete existing Ads by Id")
	public ResponseEntity<Boolean> deleteById(@RequestHeader("auth-token") String authToken, @PathVariable("id") String id){
		return service.deleteById(authToken, id);
	}

	@GetMapping("/advertise/search/{filtercriteria}")
	@ApiOperation(value = "Provide Ads by filterCriteria", notes = "Provide existing Ads By FilterCriteria")
	public ResponseEntity<Advertise> searchCriteria(@PathVariable("filtercriteria") String fc) {
		return service.searchCritetia(fc);
	}
	
	@GetMapping("/advertise/{id}")
	@ApiOperation(value = "Return advertise details", notes = "Return advertise details")
	public ResponseEntity<Advertise> getAdById(@RequestHeader("auth-token") String authToken, @PathVariable("id") String id){
		return service.getAdById(authToken, id);
	}

}
