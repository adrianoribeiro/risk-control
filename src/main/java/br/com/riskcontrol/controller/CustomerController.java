package br.com.riskcontrol.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.riskcontrol.model.Customer;
import br.com.riskcontrol.model.EnumRisk;
import br.com.riskcontrol.service.CustomerService;

/**
 * Customer controller.
 * 
 * @author adriano.ribeiro
 *
 */
@RestController
@RequestMapping("/v1/customers")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	/**
	 * Return all customers.
	 * @return list - List of customer.
	 */
	@GetMapping
	public Iterable<Customer> findAll(){
		
		return service.findAll();
	}
	
	/**
	 * Return a customer.
	 * @return customer - Customer.
	 */
	@GetMapping(value="/{id}")
	public Customer findOne(@PathVariable Long id){
		
		return service.findOne(id);
	}
	
	/**
	 * Save a customer;
	 * @param customer - Customer
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer create(@Valid @RequestBody Customer customer){
		
		return service.save(customer);
	}
	
	/**
	 * Update a customer;
	 * @param customer - Customer
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable Long id, @Valid @RequestBody Customer customer){
		
		service.save(customer);
	}
	
	/**
	 * Remove customer.
	 * @param id
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		
		service.delete(id);
	}
	
	/**
	 * Return all risks value
	 * @return list - List of EnumRisk.
	 */
	@GetMapping("/risks")
	public List<EnumRisk> getRisk(){
		
		return Arrays.asList(EnumRisk.values());
	}
}
