package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.TutorService;
@Repository
public interface TutorServiceRepository extends JpaRepository<TutorService ,Integer> {

}
