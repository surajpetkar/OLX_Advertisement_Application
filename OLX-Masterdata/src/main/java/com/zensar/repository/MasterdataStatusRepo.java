package com.zensar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.model.MasterdataStatus;

public interface MasterdataStatusRepo extends MongoRepository<MasterdataStatus, String>{

}
