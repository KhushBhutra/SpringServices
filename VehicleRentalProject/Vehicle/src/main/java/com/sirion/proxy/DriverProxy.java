package com.sirion.proxy;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sirion.entity.Driver;


@FeignClient(name = "DRIVER-SERVICE")
@LoadBalancerClient(name = "DRIVER-SERVICE")
public interface DriverProxy {

	@GetMapping("/Driver/getDriver")
	public Driver getDriver();

}