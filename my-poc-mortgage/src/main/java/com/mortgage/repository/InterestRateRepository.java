package com.mortgage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortgage.model.InterestRate;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {

	public Optional<InterestRate> findByFixedInterestRatePeriod(Integer fixedInterestRatePeriod);

}
