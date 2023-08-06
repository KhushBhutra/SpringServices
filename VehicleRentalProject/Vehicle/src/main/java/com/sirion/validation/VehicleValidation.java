package com.sirion.validation;

import org.springframework.stereotype.Component;

import com.sirion.entity.Vehicle;
import com.sirion.exception.VehicleException;

@Component
public class VehicleValidation {
	
	public String VehicleValidation(Vehicle v) throws VehicleException {
		
		if(v.getChargesPerKM()<=0 ) {
			throw new VehicleException("Invalid Charges per km");
		}
		if(v.getCapacity()<=0)
			throw new VehicleException("Invalid Capacity in Vehicle");
		if(v.getFixedCharges()<0) {
			throw new VehicleException("Invalid Fixed Charges");
		}
		if(v.getType().isBlank()) {
			throw new VehicleException("Invalid type of Vehicle");
		}
		if(v.getCategory().isBlank()) {
			throw new VehicleException("Invalid Category of Vehicle");
			
		}
		if(v.getVehicleNumber().isBlank()) {
			throw new VehicleException("Invalid Vehicle number");
			
		}
		if(v.getLocation().isBlank()) {
			throw new VehicleException("Invalid Location of Vehicle");
		}
		return "Vehicle Successfully accepted";
	}

}
