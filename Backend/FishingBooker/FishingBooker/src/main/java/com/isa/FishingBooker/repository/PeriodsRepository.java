package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.FishingBooker.model.Period;

public interface PeriodsRepository extends JpaRepository<Period, Integer> {

}
