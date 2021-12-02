package com.zensar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.model.MasterdataCategory;
import com.zensar.model.MasterdataStatus;
import com.zensar.repository.MasterdataCategoryRepo;
import com.zensar.repository.MasterdataStatusRepo;

@Service
public class MasterdataServiceImpl implements MasterdataService {

	@Autowired
	MasterdataStatusRepo srepo; 
	@Autowired
	MasterdataCategoryRepo crepo;
	
	@Override
	public ResponseEntity<List<MasterdataCategory>> category() {
		List<MasterdataCategory> mdList = crepo.findAll();
		System.out.println(mdList);
		List<MasterdataCategory> categoryList = new ArrayList<>();
		if (mdList == null)
			return new ResponseEntity<List<MasterdataCategory>>(HttpStatus.NOT_FOUND);
		else {
			for(MasterdataCategory md:mdList) {
				categoryList.add(new MasterdataCategory(md.getId(),md.getCategory()));
			}
			System.out.println(categoryList);
			return new ResponseEntity<List<MasterdataCategory>>(categoryList, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<MasterdataStatus>> getStatus() {
		List<MasterdataStatus> mdList = srepo.findAll();
		List<MasterdataStatus> statusList = new ArrayList<>();
		if (mdList == null)
			return new ResponseEntity<List<MasterdataStatus>>(HttpStatus.NOT_FOUND);
		else {
			for(MasterdataStatus md:mdList) {
				statusList.add(new MasterdataStatus(md.getId(),md.getStatus()));
			}
			return new ResponseEntity<List<MasterdataStatus>>(statusList, HttpStatus.OK);
		}
	}
}
