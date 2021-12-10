package com.isa.FishingBooker.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Boat;
@Repository
public interface BoatRepository extends JpaRepository<Boat ,Integer>{
	@Transactional
	@Modifying
	@Query(value="DELETE FROM discount_offer a WHERE a.boat_id=?1",nativeQuery = true)
	public void deleteAllDiscountOffers(int boatId);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM extras a WHERE a.boat_id=?1",nativeQuery = true)
	public void deleteAllExtras(int boatId);
}
