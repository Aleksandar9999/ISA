package com.isa.FishingBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.DiscountOffer;

@Repository
public interface DiscountOfferRepository extends JpaRepository<DiscountOffer, Integer>{

}
