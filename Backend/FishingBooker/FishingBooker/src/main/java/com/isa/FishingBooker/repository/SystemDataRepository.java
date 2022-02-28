package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.FishingBooker.model.SystemData;

public interface SystemDataRepository extends JpaRepository<SystemData, Integer> {

	@Query("select s from SystemData s where s.valid.endDate=null")
	public SystemData findCurrentlyActive();
}
