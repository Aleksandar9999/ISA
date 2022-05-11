package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.FishingBooker.model.PointsSettings;

public interface PointsSettingsRepository extends JpaRepository<PointsSettings, Integer> {
	@Query("select ps from PointsSettings ps where ps.valid.endDate=null")
	public PointsSettings findActiveSettings();
}
