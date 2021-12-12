package com.great.learning.service;

import org.springframework.stereotype.Service;

import com.great.learning.model.Customer;

import java.util.List;

@Service
public interface CustomerService {

	List<Customer> findAll();
	
	Customer findById(int id);

	void save(Customer customer);

	void deleteById(int id);
}