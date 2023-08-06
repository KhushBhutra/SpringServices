package com.sirion.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sirion.entity.Vehicle;

@FeignClient(name = "BOOKING-SERVICE")
@LoadBalancerClient(name = "BOOKING-SERVICE")
public interface BookingProxy {
	@PostMapping("booking/updateVehicle")
	public void updateBooking(List<Vehicle> li);
}
