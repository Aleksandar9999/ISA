package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;

@Repository
public interface ResortRepository extends JpaRepository<Resort ,Integer> {

	@Query("select resort from Resort resort where resort.status='CONFIRMED'")
	public List<Resort> findAllValid();
	
	@Query("select resort from Resort resort where resort.resortowner.id=?1 and resort.status='CONFIRMED'")
	public List<Resort> findAllValidByResortOwner(int resortownerId);
	
	@Query("select resort from Resort resort join fetch resort.photos photos where resort.id=?1")
	public Resort findResortWithPhotos(int boatId);
	
	@Query("select resort from Resort resort join fetch resort.discountOffers discountOffers where resort.id=?1")
	public Resort findResortWithDiscountOffers(int boatId);
	
	@Query("select resort from Resort resort where resort.id=?1")
	public Resort findResortById(int boatId);
}
