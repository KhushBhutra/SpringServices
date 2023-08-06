package com.sirion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirion.entity.Driver;
import com.sirion.entity.Vehicle;
import com.sirion.repository.VehicleRepository;

@Service
public class VehicleService implements IVehicleService {
	@Autowired
	private   VehicleRepository repo;
	
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
		if(!repo.findById(vid).isPresent())
			return null;
		return repo.findById(vid).get();
	}
	public Vehicle add(Vehicle v) {
		return repo.saveAndFlush(v);
	}
	
	public List<Vehicle> findAll(){
		// TODO Auto-generated method stub
		return  repo.findAll();
	}
	public String deleteVehicle(Vehicle v) {
		Optional<Vehicle> vid = repo.findById(v.getVehicleId());
		if(!vid.isPresent())
			return "Not Present";
		
		repo.deleteById(v.getVehicleId());
		return "Done";
	}
	public void updateDriver(Driver d1,Driver d2){
		List<Vehicle> li = repo.findByDriver(d1);
		for(Vehicle ele:li) {
			System.out.println(ele.getDriver().getContactNumber());
			ele.setDriver(d2);
			System.out.println(ele.getDriver().getContactNumber());
		}
		
	}
	public Vehicle findByType(String type) {
		List<Vehicle> li = repo.findByType(type);
		if(li.isEmpty())
			return null;
		else return li.get(0);
	}
	
	
	
}
