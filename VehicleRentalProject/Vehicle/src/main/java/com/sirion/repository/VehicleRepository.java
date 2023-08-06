package com.sirion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirion.entity.Driver;
import com.sirion.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	public List<Vehicle> findByDriver(Driver d);
	public List<Vehicle> findByType(String type);
}
