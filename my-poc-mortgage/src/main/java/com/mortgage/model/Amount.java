package com.mortgage.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable 
@NoArgsConstructor
public class Amount {
	
	@Column
	private Currency currency;

	@Column(precision=5, scale=2)
	private BigDecimal value;
	
	public Amount(BigDecimal value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	
}
