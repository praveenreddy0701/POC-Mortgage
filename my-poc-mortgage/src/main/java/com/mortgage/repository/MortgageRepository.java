package com.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mortgage.model.Mortgage;

public interface MortgageRepository extends JpaRepository<Mortgage, Long> {

}
