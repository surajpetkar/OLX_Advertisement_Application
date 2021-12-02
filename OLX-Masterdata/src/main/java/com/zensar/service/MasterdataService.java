package com.zensar.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zensar.model.MasterdataCategory;
import com.zensar.model.MasterdataStatus;

public interface MasterdataService {

	ResponseEntity<List<MasterdataCategory>> category();

	ResponseEntity<List<MasterdataStatus>> getStatus();
}
