package com.zensar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.model.User;

public interface LoginRepository extends MongoRepository<User,String>{

	public User findByUserName(String username);
}
