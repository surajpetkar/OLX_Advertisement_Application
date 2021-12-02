package com.zensar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.model.Advertise;

public interface AdvertiseRepository extends MongoRepository<Advertise, String> {

}
