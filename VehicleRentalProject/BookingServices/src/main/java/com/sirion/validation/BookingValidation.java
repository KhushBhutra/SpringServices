package com.sirion.validation;

import java.time.LocalDate;

import com.sirion.entity.Booking;

public class BookingValidation {
	public boolean DateValidation(Booking obj) {
		LocalDate d1 = obj.getBookingDate();
		LocalDate d2 = obj.getBookedTillDate();
		int val = d2.compareTo(d1);
		if(val<0)
			return false;
		
		if(obj.getCustomer() == null )
			return false;
		
		if(obj.getVehicle() == null)
			return false;
		
		if(obj.getDistance()<=0)
			return false;
		
		return true;
	}
}
