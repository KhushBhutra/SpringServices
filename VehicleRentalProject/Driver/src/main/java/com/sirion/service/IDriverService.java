package com.sirion.service;

import java.util.List;

import com.sirion.entity.Driver;

public interface IDriverService {
	public void add(Driver d) ;
	public Driver findById(int id); 
	public List<Driver> findAll();
	public void removeDriver(Driver d) ;
	public void updateDriver(Driver d);
}
