package com.sirion.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sirion.entity.Booking;
import com.sirion.entity.Customer;
import com.sirion.entity.Vehicle;
import com.sirion.exception.BookingNotExistsException;
import com.sirion.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository repo ;
	public Booking add(Booking b) {
		return repo.saveAndFlush(b);
	}
	public List<Booking> findAll(){
		return repo.findAll();
	}
	public List<Booking> findByCustomer( Customer cust ){
		return repo.findByCustomer(cust);
	}
	public List<Booking> findByBookingDate(LocalDate d){
		return repo.findByBookingDate(d);
	}
	public List<Booking> getBookingByVehicle(Vehicle v){
		List<Booking> li = repo.findByVehicle(v);
//		if(li.isEmpty()) {
//			throw new BookingNotExistsException("No Such Booking from this vehicle");
//		}
		return li; 
	}
	
	public Booking findById(int id) {
		if(!repo.findById(id).isPresent())
			return null;
		return repo.findById(id).get();
	}
	public void deleteById(int bId) {
		repo.deleteById(bId);
	}
	public String updateBooking(Booking b) {
		Booking existsBooking = repo.findById(b.getBookingId()).orElse(null);
		if(existsBooking == null) {
			throw new BookingNotExistsException( "No Such Booking Exists");
		}
		else {
			repo.saveAndFlush(b);
			return "Booking Updated successfully";
		}
	}
	public void updateVehicle(Vehicle v1,Vehicle v2) {
		List<Booking> li = repo.findByVehicle(v1);
		for(Booking b:li) {
			b.setVehicle(v2);
		}
		
	}
}
