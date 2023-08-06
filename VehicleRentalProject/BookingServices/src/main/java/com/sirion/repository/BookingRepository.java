package com.sirion.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirion.entity.Booking;
import com.sirion.entity.Customer;
import com.sirion.entity.Vehicle;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public List<Booking> findByCustomer(Customer obj);
	public List<Booking> findByBookingDate(LocalDate d);
	public List<Booking> findByVehicle(Vehicle v);
}
