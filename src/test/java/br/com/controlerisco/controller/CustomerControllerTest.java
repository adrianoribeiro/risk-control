package br.com.controlerisco.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.riskcontrol.controller.CustomerController;
import br.com.riskcontrol.model.Customer;
import br.com.riskcontrol.model.EnumRisk;
import br.com.riskcontrol.service.CustomerService;

@RunWith(PowerMockRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController controller;
	
	@Mock
	private CustomerService service;
	
	//To run validate.
	private static Validator validator;

	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer.setName("Adriano");
		customer.setCreditLimit(new BigDecimal(1000));
		
		//To run validate.
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void saveComSucesso(){
		
		customer.setRisk(EnumRisk.A);
		controller.create(this.customer);
		verify(service).save(Matchers.any(Customer.class));

		//Run validate
		Set<ConstraintViolation<Customer>> violations = validator.validate(this.customer);
        assertTrue(violations.isEmpty());
	}
	
	@Test
	public void saveSemSucesso(){
		
		//because these lines 3 errors must occur.
		this.customer.setName(null);
		this.customer.setCreditLimit(null);
		this.customer.setRisk(null);
		
		controller.create(this.customer);
		verify(service).save(Matchers.any(Customer.class));

		Set<ConstraintViolation<Customer>> violations = validator.validate(this.customer);
		violations.stream().forEach(error -> System.out.println(error));
		assertEquals(violations.size(), 3);
	}
	
	@Test
	public void findOne(){
		controller.findOne(this.customer.getId());
		verify(service).findOne(this.customer.getId());
	}
	
	@Test
	public void findAll(){
		controller.findAll();
		verify(service).findAll();
	}
	
	@Test
	public void delete(){
		controller.delete(this.customer.getId());
		verify(service).delete(this.customer.getId());
	}

}
