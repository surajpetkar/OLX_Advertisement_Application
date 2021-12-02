package com.zensar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.model.MasterdataCategory;
import com.zensar.model.MasterdataStatus;
import com.zensar.service.MasterdataService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MasterdataController {
	
	@Autowired
	MasterdataService service;
	
	@GetMapping("/advertise/category")
	@ApiOperation(value="Provide Category", notes="This service provides All category of products")
	public ResponseEntity<List<MasterdataCategory>> category(){
		return service.category();
	}
	
	@GetMapping(value = "/advertise/status")
	@ApiOperation(value = "Get Status of Ads", notes = "Returns all possible advertise status")
	public ResponseEntity<List<MasterdataStatus>> getStatus() {
		return service.getStatus();
	}
}
