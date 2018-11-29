	package com.mortgage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.model.InterestRate;
import com.mortgage.model.Mortgage;
import com.mortgage.services.MortgageService;

@RestController
@RequestMapping("/api")
public class MortgageController {

	@Autowired
	MortgageService mortgageService;

	@GetMapping("/interest-rates")
	public ResponseEntity<List<InterestRate>> getInterestRates() {
		List<InterestRate> irList = mortgageService.findAllInterestRates();
		if (!irList.isEmpty()) {
			return new ResponseEntity<>(irList, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/mortgage-check")
	public ResponseEntity<?> checkMortgage(@RequestBody Mortgage mortgage) {
		Mortgage mortgageReturn = mortgageService.checkMortgage(mortgage);
		if (mortgageReturn != null)
			return new ResponseEntity<>(mortgageReturn, HttpStatus.OK);
		return new ResponseEntity<>(mortgageReturn, HttpStatus.BAD_REQUEST);
	}

}
