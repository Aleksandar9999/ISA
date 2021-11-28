package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Objection;

@Repository
public interface ObjectionRepository extends JpaRepository<Objection, Integer> {

}
