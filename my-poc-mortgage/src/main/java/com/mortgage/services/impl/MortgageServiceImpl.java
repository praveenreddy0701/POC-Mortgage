package com.mortgage.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.model.Amount;
import com.mortgage.model.InterestRate;
import com.mortgage.model.Mortgage;
import com.mortgage.repository.InterestRateRepository;
import com.mortgage.repository.MortgageRepository;
import com.mortgage.services.MortgageService;

@Service
public class MortgageServiceImpl implements MortgageService {

	@Autowired
	InterestRateRepository irRepository;

	@Autowired
	MortgageRepository mortRepository;

	@Override
	public List<InterestRate> findAllInterestRates() {
		return irRepository.findAll();
	}

	@Override
	public Optional<InterestRate> findByFixedInterestRatePeriod(Integer fixedInterestRatePeriod) {
		return irRepository.findByFixedInterestRatePeriod(fixedInterestRatePeriod);
	}

	@Override
	public Mortgage checkMortgage(Mortgage mortgage) {

		// find by the maturity level
		Optional<InterestRate> optIR = irRepository
				.findByFixedInterestRatePeriod(mortgage.getFixedInterestRatePeriod());
		Mortgage mortReturn = new Mortgage(mortgage);

		if (optIR.isPresent()) {
			InterestRate ir = optIR.get();
			Double monthlyRate = calculateMonthlyRate(ir.getInterestRate());
			Double rateFactor = calculateRateFactor(monthlyRate, mortReturn.getFixedInterestRatePeriod());
			Double monthlyPayment = calculateMonthlyPayment(rateFactor, mortReturn.getLoanValue().getValue(),
					monthlyRate);

			BigDecimal mP = BigDecimal.valueOf(monthlyPayment).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			mortReturn.setMortgageMonthlyCost(new Amount(mP, mortgage.getLoanValue().getCurrency()));
			mortReturn.setIsMortgageApplicable(true);
			if (monthlyPayment > (mortReturn.getIncome().getValue().doubleValue() / 4)) {
				mortReturn.setIsMortgageApplicable(false);
				return mortReturn;
			} else if (monthlyPayment * mortReturn.getFixedInterestRatePeriod() * 12 > mortgage.getHomeValue()
					.getValue().doubleValue()) {
				mortReturn.setIsMortgageApplicable(false);
				return mortReturn;
			}
		} else {
			mortReturn.setIsMortgageApplicable(false);
		}

		return mortReturn;
	}

	private Double calculateMonthlyPayment(Double rateFactor, BigDecimal principalAmount, Double monthlyRate) {
		Double aux = (monthlyRate * rateFactor) / (rateFactor - 1);
		return principalAmount.doubleValue() * aux;
	}

	private Double calculateRateFactor(Double montlhyRate, Integer fixedPeriod) {
		return Math.pow((1 + montlhyRate), fixedPeriod * 12);
	}

	private Double calculateMonthlyRate(BigDecimal anualRate) {
		return (anualRate.doubleValue() / 12) / 100;
	}

}
