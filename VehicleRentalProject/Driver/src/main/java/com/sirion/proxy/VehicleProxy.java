package com.sirion.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sirion.entity.Driver;

@FeignClient(name = "VEHICLE-SERVICE")
@LoadBalancerClient(name = "VEHICLE-SERVICE")
public interface VehicleProxy {
	@PostMapping("/vehicle/updateDriver")
	public void update_driver(@RequestBody List<Driver> d);
	
}
