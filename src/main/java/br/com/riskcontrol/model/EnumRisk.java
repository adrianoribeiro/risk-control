package br.com.riskcontrol.model;

/**
 * Enum risk
 * @author Adriano
 *
 */
public enum EnumRisk {
	A(1.0),B(1.1),C(1.2);
	
	private Double rate;
	
	public Double getRate() {
		return rate;
	}

	/**
	 * 
	 * @param rate
	 */
	private EnumRisk(Double rate){
		this.rate = rate;
	}
}
