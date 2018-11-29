package com.mortgage.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Mortgage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "currency", column = @Column(name = "income_currency")),
			@AttributeOverride(name = "value", column = @Column(name = "income_value")) })
	private Amount income;

	@Column(name = "fixedInterestRatePeriod", nullable = false)
	private Integer fixedInterestRatePeriod;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "currency", column = @Column(name = "loanvalue_currency")),
			@AttributeOverride(name = "value", column = @Column(name = "loanvalue_value")) })
	private Amount loanValue;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "currency", column = @Column(name = "homevalue_currency")),
			@AttributeOverride(name = "value", column = @Column(name = "homevalue_value")) })
	private Amount homeValue;

	@Column(name = "isMortgageApplicable", nullable = true)
	private Boolean isMortgageApplicable;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "mortgageMonthlyCost_currency")),
			@AttributeOverride(name = "value", column = @Column(name = "mortgageMonthlyCost_value")) })
	private Amount mortgageMonthlyCost;

	public Mortgage() {

	}

	public Mortgage(Mortgage mortgage) {
		this.id = mortgage.id;
		this.homeValue = mortgage.homeValue;
		this.loanValue = mortgage.loanValue;
		this.fixedInterestRatePeriod = mortgage.fixedInterestRatePeriod;
		this.income = mortgage.income;
		this.mortgageMonthlyCost = mortgage.mortgageMonthlyCost;
		this.isMortgageApplicable = mortgage.isMortgageApplicable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Amount getIncome() {
		return income;
	}

	public void setIncome(Amount income) {
		this.income = income;
	}

	public Integer getFixedInterestRatePeriod() {
		return fixedInterestRatePeriod;
	}

	public void setFixedInterestRatePeriod(Integer fixedInterestRatePeriod) {
		this.fixedInterestRatePeriod = fixedInterestRatePeriod;
	}

	public Amount getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(Amount loanValue) {
		this.loanValue = loanValue;
	}

	public Amount getHomeValue() {
		return homeValue;
	}

	public void setHomeValue(Amount homeValue) {
		this.homeValue = homeValue;
	}

	public Boolean getIsMortgageApplicable() {
		return isMortgageApplicable;
	}

	public void setIsMortgageApplicable(Boolean isMortgageApplicable) {
		this.isMortgageApplicable = isMortgageApplicable;
	}

	public Amount getMortgageMonthlyCost() {
		return mortgageMonthlyCost;
	}

	public void setMortgageMonthlyCost(Amount mortgageMonthlyCost) {
		this.mortgageMonthlyCost = mortgageMonthlyCost;
	}

}
