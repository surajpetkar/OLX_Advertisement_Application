package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.exception.InvalidUserException;
import com.zensar.model.Advertise;
import com.zensar.repository.AdvertiseRepository;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	AdvertiseRepository repo;
	@Autowired
	LoginDelegate loginDelegate;
	@Autowired
	MasterDataDelegate masterDataDelegate;

	List<Advertise> adList = new ArrayList<>();
	int adId = 0;

	@Override
	public ResponseEntity<Advertise> create(String authToken, Advertise ad) {
		/*
		 * if(loginDelegate.isvalidToken(authToken)) { throw new
		 * InvalidUserException(authToken); }
		 */
		repo.save(ad);
		// for OLX - Master Data Service
		String status = masterDataDelegate.getStatusById(ad.getStatus());
		ad.setStatus(status);
		return new ResponseEntity<Advertise>(ad, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Advertise> update(String authToken, Advertise ad, String id) {
		if (loginDelegate.isvalidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ads = null;
			if (repo.findById(id) != null) {
				ads = repo.findById(id);
				String newId = ads.get().getId();
				repo.deleteById(newId);
				ad.setId(newId);
				repo.save(ad);
				return new ResponseEntity<Advertise>(ad, HttpStatus.OK);
			} else
				return new ResponseEntity<Advertise>(ad, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Advertise> getAll(String authToken) {
		if (loginDelegate.isvalidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			List<Advertise> adsList = repo.findAll();
			if (!adsList.isEmpty())
				return adsList;
			else
				return null;
		}
	}

	@Override
	public ResponseEntity<Advertise> getById(String authToken, String id) {
		if (loginDelegate.isvalidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ad = null;
			if (repo.findById(id) != null) {
				ad = repo.findById(id);
				return new ResponseEntity<Advertise>(ad.get(), HttpStatus.FOUND);
			} else
				return new ResponseEntity<Advertise>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteById(String authToken, String id) {
		if (loginDelegate.isvalidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			if (repo.findById(id) != null)
				repo.deleteById(id);
			else
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			List<Advertise> adList = new ArrayList<>();
			adList = repo.findAll();
			if (!adList.contains(repo.findById(id)))
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Advertise> searchCritetia(String fc) {
		List<Advertise> adList = repo.findAll();
		for (int i = 0; i < adList.size(); i++) {
			if (fc.equals(adList.get(i).getCategory()))
				return new ResponseEntity<Advertise>(adList.get(i), HttpStatus.FOUND);
		}
		return new ResponseEntity<Advertise>(adList.get(0), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Advertise> getAdById(String authToken, String id) {
		if (loginDelegate.isvalidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ad = null;
			if (repo.findById(id) != null) {
				ad = repo.findById(id);
				return new ResponseEntity<Advertise>(ad.get(), HttpStatus.FOUND);
			} else
				return new ResponseEntity<Advertise>(HttpStatus.NOT_FOUND);
		}	
	}

}
