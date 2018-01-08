package br.com.controlerisco.service;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.riskcontrol.exceptions.NegativeCreditLimitException;
import br.com.riskcontrol.model.Customer;
import br.com.riskcontrol.model.EnumRisk;
import br.com.riskcontrol.repository.CustomerRepository;
import br.com.riskcontrol.service.CustomerService;

@RunWith(PowerMockRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService service;
	
	@Mock
	private CustomerRepository repository;
	
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer.setName("Adriano");
		customer.setCreditLimit(new BigDecimal(1000));
	}
	
	@Test
	public void riskA() {
		customer.setRisk(EnumRisk.A);
		service.save(customer);
		
		verify(repository).save(customer);
		assertEquals(BigDecimal.ZERO, customer.getRate());
	}
	
	@Test
	public void riskB() {
		customer.setRisk(EnumRisk.B);
		service.save(customer);
		
		verify(repository).save(customer);
		
		assertEquals(0, customer.getRate().setScale(2, RoundingMode.DOWN).compareTo(new BigDecimal(100.00)));
	}
	
	@Test
	public void riskC() {
		customer.setRisk(EnumRisk.C);
		service.save(customer);
		
		verify(repository).save(customer);
		
		assertEquals(0, customer.getRate().setScale(2, RoundingMode.DOWN).compareTo(new BigDecimal(200.00)));
	}

	@Test(expected=NegativeCreditLimitException.class)
	public void negativeCreditLimit() {
		customer.setCreditLimit(new BigDecimal(-1));
		customer.setRisk(EnumRisk.C);
		service.save(customer);
	}
}
