package com.sirion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirion.entity.Booking;
import com.sirion.entity.Driver;
import com.sirion.entity.Vehicle;
import com.sirion.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private   VehicleRepository repo;
	@Autowired
	private DriverService ds;
//	
//	{
//		Driver d = new Driver();
//		Vehicle v1 = new Vehicle(100,d,"CTX-5980","Car","AC","4Seater","Gurgaon",4,35,250);
//		Vehicle v2 = new Vehicle(101,d,"CTX-5980","BUS","NON-AC","4Seater","Gurgaon",4,35,250);
//		Vehicle v3 = new Vehicle(102,d,"CTX-5980","Car","NON-AC","4Seater","Gurgaon",4,35,250);
//		repo.saveAndFlush(v1);
//		repo.saveAndFlush(v2);
//		repo.saveAndFlush(v3);
//	}
	public Vehicle findById(int vid) {
		return repo.findById(vid).get();
	}
	public void add(Vehicle v) {
		repo.saveAndFlush(v);
	}
	
	public List<Vehicle> findAll(){
		// TODO Auto-generated method stub
		return  repo.findAll();
	}
	
	
}
