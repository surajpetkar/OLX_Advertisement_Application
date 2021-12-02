package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.model.User;

public interface LoginRepository extends JpaRepository<User,String>{

	public User findByUserName(String username);
}
