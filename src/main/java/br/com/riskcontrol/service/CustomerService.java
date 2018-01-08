package br.com.riskcontrol.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riskcontrol.exceptions.NegativeCreditLimitException;
import br.com.riskcontrol.model.Customer;
import br.com.riskcontrol.repository.CustomerRepository;

/**
 * 
 * Customer Service.
 * 
 * @author adriano.ribeiro
 *
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	/**
	 * Return all customers.
	 * @return list - List of customer.
	 */
	public Iterable<Customer> findAll(){
		return repository.findAll();
	}

	/**
	 * Return a customer.
	 * @return customer - customer.
	 */
	public Customer findOne(final Long id){
		return repository.findOne(id);
	}
	
	/**
	 * Save a customer;
	 * @param customer - Customer
	 * @return customer - Customer 
	 */
	public Customer save(final Customer customer){
		
		validate(customer);
		
		applyRate(customer);
		
		return repository.save(customer);
	}

	/**
	 * Validate entity values.
	 * 
	 * @param customer - Customer
	 */
	private void validate(Customer customer) {
		
		if (isNegativeCreditLimit(customer.getCreditLimit())){
			throw new NegativeCreditLimitException();
		}
		
		//Other validations...
	}

	private boolean isNegativeCreditLimit(BigDecimal creditLimit) {
		return BigDecimal.ZERO.compareTo(creditLimit)==1;
	}

	private void applyRate(final Customer customer) {
		final BigDecimal rateValue = customer.getCreditLimit();
		
		customer.setRate(rateValue.multiply(new BigDecimal(customer.getRisk().getRate())));
	}

	/**
	 * Remove customer.
	 * @param id - Long
	 */
	public void delete(final Long id){
		repository.delete(id);
	}
}
