package com.sirion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirion.entity.Customer;
import com.sirion.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repo;
	
	public Customer getCustomer(int custId) {
		return repo.findById(custId).get();
	}
	public void add(Customer c) {
		repo.saveAndFlush(c);
	}
	public List<Customer> findAll(){
		return repo.findAll();
	}
}
