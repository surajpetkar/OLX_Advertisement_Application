package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.model.MasterdataCategory;

public interface MasterdataCategoryRepo extends JpaRepository<MasterdataCategory, String> {

}
