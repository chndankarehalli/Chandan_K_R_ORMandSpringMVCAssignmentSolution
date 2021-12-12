package com.great.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.great.learning.model.Customer;
import com.great.learning.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listBooks(Model theModel) {

		System.out.println("Inside the CustomerController -> ");

		List<Customer> theCustomer = customerService.findAll();

		theModel.addAttribute("Customer", theCustomer);

		return "list-customer";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("Customer", theCustomer);
		return "Customer-form";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println("Trying to save with Name :" + firstName + " " + lastName);

		Customer theCustomer;
		if (id != 0) {

			theCustomer = customerService.findById(id);

			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);

		} else {
			theCustomer = new Customer(firstName, lastName, email);
		}

		customerService.save(theCustomer);
		return "redirect:/customer/list";
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		customerService.deleteById(theId);
		return "redirect:/customer/list";
	}
}