package com.isa.FishingBooker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Extras;
@Repository
public interface ExtrasRepository extends JpaRepository<Extras, Integer> {

}