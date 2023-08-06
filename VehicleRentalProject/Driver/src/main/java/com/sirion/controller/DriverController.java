package com.sirion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirion.Exception.DriverException;
import com.sirion.entity.Driver;
import com.sirion.proxy.VehicleProxy;
import com.sirion.service.DriverService;
import com.sirion.validation.ValidationDriver;

import feign.Body;

@RestController
@RequestMapping("Driver")
public class DriverController {
	@Autowired
	private DriverService ds;
	@Autowired
	private VehicleProxy vproxy;
	@Autowired
	private ValidationDriver validDriver;
	
	@PostMapping
//	public List<Driver> addDriver(int id,String firstName,String lastName,String contactNo,String email,String address,double chargesPerDay,String licenseNo){
	public ResponseEntity<String> addDriver(@RequestBody Driver d){
//		Driver d = new Driver(id,firstName,lastName,contactNo,email,address,chargesPerDay,licenseNo);
		
		
		try {
			validDriver.DriverValidation(d);
			ds.add(d);
		} catch (DriverException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>("Created Succesfully", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDriver(@PathVariable int id){
		Driver d = ds.findById(id);
		if(d == null) {
			return new ResponseEntity<>("No Driver With Id:"+d.getDriverId(), HttpStatus.BAD_REQUEST);
		}
		List<Driver> li = new ArrayList<Driver>();
		li.add(d);
		
		ds.removeDriver(d);
		List<Driver> li1 = ds.findAll();
		Driver d1 = null;
		if(li1.isEmpty())
			li.add(d1);
		else li.add(li1.get(0));
		vproxy.update_driver(li);
		return new ResponseEntity<>("Deleted Succesfully", HttpStatus.ACCEPTED);
		
	}
	@PutMapping
	public ResponseEntity<String> updateDriver(@RequestBody Driver d) {
		if(d == null) {
			return new ResponseEntity<>("Driver is Sent properly", HttpStatus.BAD_REQUEST);
		}
		Driver d1 = ds.findById(d.getDriverId());
		if(d1 == null) {
			return new ResponseEntity<>("No Driver With Id:"+d.getDriverId(), HttpStatus.BAD_REQUEST);
		}
		try {
			validDriver.DriverValidation(d);
//			ds.add(d);
			ds.updateDriver(d);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		List<Driver> li = new ArrayList<Driver>();
		li.add(d1);
		li.add(d);
		vproxy.update_driver(li);
		
		return new ResponseEntity<String>("Updated Successfully of Driver with Id: "+d.getDriverId(), HttpStatus.ACCEPTED);
	}
	@GetMapping("all")
	public List<Driver> getAll(){
		
		return ds.findAll();
	}
	@GetMapping("/id/{id}")
	public Driver getById(@PathVariable int id) {
		return ds.findById(id);
	}
	@GetMapping("getDriver")
	public Driver getDriverVehicle() {
		List<Driver> li = ds.findAll();
		if(li.isEmpty())
			return null;
		else return li.get(0);
	}
}
