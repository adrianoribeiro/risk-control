package br.com.riskcontrol.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Customer class
 * 
 * @author adriano.ribeiro
 *
 */
@Entity
public class Customer {

	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty(message = "{customer.name.vazio}")
	private String name;

	@NotNull(message = "{customer.limite.null}")
	private BigDecimal creditLimit;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "{customer.risk.vazio}")
	private EnumRisk risk;
	
	private BigDecimal rate;

	public Customer() {
		this.setRate(BigDecimal.ZERO);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public EnumRisk getRisk() {
		return risk;
	}

	public void setRisk(EnumRisk risk) {
		this.risk = risk;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
