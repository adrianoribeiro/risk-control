package br.com.riskcontrol.model;

/**
 * Enum risk
 * @author Adriano
 *
 */
public enum EnumRisk {
	A(0.0), B(0.1), C(0.2);
	
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
