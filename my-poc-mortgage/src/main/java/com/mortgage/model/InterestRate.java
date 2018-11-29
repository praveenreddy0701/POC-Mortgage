package com.mortgage.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class InterestRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "fixedInterestRatePeriod", nullable = false)
	private Integer fixedInterestRatePeriod;

	@Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
	private BigDecimal interestRate;

	@Column(name = "last_update", nullable = false)
	private Date lastUpdate;

	public InterestRate(Integer fixedInterestRatePeriod, BigDecimal interestRate) {
		this.fixedInterestRatePeriod = fixedInterestRatePeriod;
		this.interestRate = interestRate;
		this.lastUpdate = Date.valueOf(LocalDate.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFixedInterestRatePeriod() {
		return fixedInterestRatePeriod;
	}

	public void setFixedInterestRatePeriod(Integer fixedInterestRatePeriod) {
		this.fixedInterestRatePeriod = fixedInterestRatePeriod;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
