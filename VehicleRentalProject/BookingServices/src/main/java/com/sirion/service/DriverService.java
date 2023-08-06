package com.sirion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirion.entity.Driver;
import com.sirion.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository repo ;
	
//	static {
//		Driver d1 = new Driver(100, "Rakesh", "Narayan", "9306782403", "Rakesh12@gmail.com", "E-23GopalNagar", 2000, "QWERTY-12CDTX");
//		Driver d2 = new Driver(101, "Aakash", "Narayan", "9306782406", "Aakash12@gmail.com", "B-23GopalNagar", 2200, "QWERTY-12CUTX");
//		Driver d3 = new Driver(102, "Dipesh", "Narayan", "9306782405", "Dipessh12@gmail.com", "C-23GopalNagar", 1800, "QWERTY-12YDTX");
//		repo.saveAndFlush(d1);
//		repo.saveAndFlush(d2);
//		repo.saveAndFlush(d3);
//	}
	
	public void add(Driver d) {
		repo.saveAndFlush(d);
	}
	
	public Driver findById(int id) {
		return repo.findById(id).get();
		
	}
	public List<Driver> findAll(){
		return repo.findAll();
	}

}
