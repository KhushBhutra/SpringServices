package com.sirion.service;

import java.util.List;

import com.sirion.entity.Driver;
import com.sirion.entity.Vehicle;

public interface IVehicleService {
	public Vehicle findById(int vid) ;
	public Vehicle add(Vehicle v) ;
	public List<Vehicle> findAll();
	public void updateDriver(Driver d1,Driver d2);
}
