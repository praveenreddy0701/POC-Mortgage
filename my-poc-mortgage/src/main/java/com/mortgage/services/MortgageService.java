package com.mortgage.services;

import java.util.List;
import java.util.Optional;

import com.mortgage.model.InterestRate;
import com.mortgage.model.Mortgage;

public interface MortgageService {

	public List<InterestRate> findAllInterestRates();

	public Optional<InterestRate> findByFixedInterestRatePeriod(Integer fixedInterestRatePeriod);

	public Mortgage checkMortgage(Mortgage mortgage);

}
