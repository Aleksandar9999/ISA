package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Boat;
@Repository
public interface BoatRepository extends JpaRepository<Boat ,Integer>{

}
