package com.sirion.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sirion.entity.Booking;
import com.sirion.entity.Customer;
import com.sirion.entity.Driver;
import com.sirion.entity.Vehicle;
import com.sirion.proxy.CustomerProxy;
import com.sirion.proxy.VehicleProxy;
import com.sirion.repository.BookingRepository;
import com.sirion.repository.VehicleRepository;
import com.sirion.service.BookingService;
import com.sirion.service.CustomerService;
import com.sirion.service.DriverService;
import com.sirion.service.VehicleService;

import jakarta.validation.constraints.Null;

class CombinedClass{
	private String type;
	private Booking b;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Booking getB() {
		return b;
	}
	public void setB(Booking b) {
		this.b = b;
	}
	
}

@RestController
@RequestMapping("booking")
public class BookingController {
	@Autowired
	private BookingService bs;
	@Autowired
	private CustomerService cs;
	@Autowired
	private VehicleService vs;
	@Autowired
	private DriverService ds;

//	@Autowired
//	private CustomerProxy custProxy;
	@Autowired
	private VehicleProxy vehicleProxy;
	
	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@GetMapping("viewall")
	public List<Booking> getAllBooking() {
		List<Booking> li = bs.findAll();
		return li;
	}

	@GetMapping("viewByCustomer/{obj}")
	public List<Booking> getBookingByCustomer(@RequestBody Customer cust) {
		List<Booking> li = bs.findByCustomer(cust);
		return li;
	}

	@GetMapping("viewByDate/{bookingDate}")
	public List<Booking> getBookingFromDate(@PathVariable("bookingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate bookingDate) {
//		bookingDate = bookingDate.toString();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//		LocalDate d = LocalDate.parse(bookingDate);
		List<Booking> li = bs.findByBookingDate(bookingDate);
		return li;
	}

	@GetMapping("viewByVehicle/{vid}")
	public List<Booking> getBookingByVehicle(@PathVariable int vid) {
		Vehicle v = vs.findById(vid);

		List<Booking> li = bs.getBookingByVehicle(v);
		return li;
	}

	@GetMapping("{id}")
	public Booking getBooking(@PathVariable int id) {
		return bs.findById(id);
	}

	@GetMapping("GetAllCustomer")
	public List<Customer> getAllCustomer() {
		return cs.findAll();
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody CombinedClass obj1) {
//		Vehicle v = vs.findById(vid);
//		String type = ;
		Booking b = obj1.getB();
		String type = obj1.getType();
		System.out.println(type);
		if(b == null)
			return new ResponseEntity<String>("Booking is Invalid", HttpStatus.BAD_REQUEST);
		if(type == null || (type.equals("Car") == false && type.equals("Bus") == false))
			return new ResponseEntity<String>("Select type in : Car or Bus", HttpStatus.BAD_REQUEST);
		
		Vehicle v = vehicleProxy.getVehicle(type);
		Booking obj = new Booking();
		
		if(v!=null && v.getDriver()!=null) {
//			b.setCustomer(cust);
			
			b.setVehicle(v);
		    obj = bs.add(b);
		}
		else {
			if(v== null) {
				logger.error("Vehicle Not present");
				return new ResponseEntity<String>("Vehicle Not Present", HttpStatus.BAD_REQUEST);
			}
			else {
				logger.error("Driver Not present any");
				return new ResponseEntity<String>("Driver Not Present", HttpStatus.BAD_REQUEST);
			}
		}
//		Customer cust = cs.getCustomer(custId);
		
		return new ResponseEntity<String>("Successfully Booked with id:"+obj.getBookingId(), HttpStatus.ACCEPTED);
	}

	
	@PostMapping("updateVehicle")
	public void updateBookingForVehicle(@RequestBody List<Vehicle> v) {
		
		Vehicle v1 = v.get(0);
		Vehicle v2 = v.get(1);
		bs.updateVehicle(v1,v2);
	}

	@PutMapping
	public ResponseEntity<String> update(@RequestBody CombinedClass obj1) {
		Booking b = obj1.getB();
		String type = obj1.getType();
		int bId = b.getBookingId();
		Booking b1 = bs.findById(bId);
		
		if(b1 == null)
			return new ResponseEntity<String>("No Booking with this Id", HttpStatus.BAD_REQUEST);
		if(type == null || (type.equals("Car") == false && type.equals("Bus") == false))
			return new ResponseEntity<String>("Select type in : Car or Bus", HttpStatus.BAD_REQUEST);
		
		if(b1.getVehicle().getType() != type) {
			Vehicle v = vehicleProxy.getVehicle(type);
			
			if(v!=null && v.getDriver()!=null) {	
				b.setVehicle(v);
			}
			else {
				if(v== null) {
					logger.error("Vehicle Not present");
					return new ResponseEntity<String>("Vehicle Not Present", HttpStatus.BAD_REQUEST);
				}
				else {
					logger.error("Driver Not present any");
					return new ResponseEntity<String>("Driver Not Present", HttpStatus.BAD_REQUEST);
				}
			}
		}
		else {
			b.setVehicle(b1.getVehicle());
		}
		bs.add(b);
		return new ResponseEntity<String>("Booking Updated with this id:"+bId, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("{bId}")
	public ResponseEntity<String> cancalBooking(@PathVariable int  bId) {
//		int bId = b.getBookingId();
		Booking b1 = bs.findById(bId);
		if(b1 == null)
			return new ResponseEntity<String>("No Booking with this Id", HttpStatus.BAD_REQUEST);
		bs.deleteById(bId);
		return  new ResponseEntity<String>("Booking Delete with this id:"+bId, HttpStatus.ACCEPTED);
	}
}