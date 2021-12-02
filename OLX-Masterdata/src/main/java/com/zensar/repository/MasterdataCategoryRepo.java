package com.zensar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.model.MasterdataCategory;

public interface MasterdataCategoryRepo extends MongoRepository<MasterdataCategory, String> {

}
