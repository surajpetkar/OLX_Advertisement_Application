package com.zensar.repository;

import org.springframework.data.mongodb.repository.JpaRepository;

import com.zensar.model.Advertise;

public interface AdvertiseRepository extends JpaRepository<Advertise, String> {

}
