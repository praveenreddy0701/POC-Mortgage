package com.mortgage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mortgage.model.InterestRate;
import com.mortgage.repository.InterestRateRepository;
import com.mortgage.repository.MortgageRepository;

@SpringBootApplication
public class MortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InterestRateRepository repository, MortgageRepository mortRepository) {
		return (args) -> {
			// save a couple of interest rates

			List<InterestRate> irList = Arrays.asList(new InterestRate[] {
					new InterestRate(1, BigDecimal.valueOf(4.505)), new InterestRate(2, BigDecimal.valueOf(4.495)),
					new InterestRate(3, BigDecimal.valueOf(4.455)), new InterestRate(4, BigDecimal.valueOf(4.415)),
					new InterestRate(5, BigDecimal.valueOf(4.395)), new InterestRate(6, BigDecimal.valueOf(4.355)),
					new InterestRate(7, BigDecimal.valueOf(4.315)), new InterestRate(8, BigDecimal.valueOf(4.295)),
					new InterestRate(9, BigDecimal.valueOf(4.255)), new InterestRate(10, BigDecimal.valueOf(4.215)),
					new InterestRate(11, BigDecimal.valueOf(4.195)), new InterestRate(12, BigDecimal.valueOf(4.155)),
					new InterestRate(13, BigDecimal.valueOf(4.115)), new InterestRate(14, BigDecimal.valueOf(4.095)),
					new InterestRate(15, BigDecimal.valueOf(4.055)), new InterestRate(16, BigDecimal.valueOf(4.015)),
					new InterestRate(17, BigDecimal.valueOf(3.995)), new InterestRate(18, BigDecimal.valueOf(3.955)),
					new InterestRate(19, BigDecimal.valueOf(3.915)),
					new InterestRate(20, BigDecimal.valueOf(3.895)), });

			repository.saveAll(irList);

			// add a mortgage in order to know the json structure
//			Mortgage mort = new Mortgage();
//			mort.setHomeValue(new Amount(BigDecimal.valueOf(300000), Currency.EUR));
//			mort.setIncome(new Amount(BigDecimal.valueOf(10000), Currency.EUR));
//			mort.setLoanValue(new Amount(BigDecimal.valueOf(200000), Currency.EUR));
//			mort.setFixedInterestRatePeriod(15);
//			mort.setIsMortgageApplicable(true);
//			//
//			mortRepository.saveAndFlush(mort);

		};
	}
}
