package com.isa.FishingBooker.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Tutor;
@Repository
public interface BoatRepository extends JpaRepository<Boat ,Integer>{

	@Query("select boat from Boat boat where boat.status='CONFIRMED'")
	public List<Boat> findAllValid();
	
	@Query("select boat from Boat boat where boat.boatowner.id=?1 and boat.status='CONFIRMED'")
	public List<Boat> findAllValidByBoatOwner(int boatownerId);
	
	@Query("select boat from Boat boat join fetch boat.photos photos where boat.id=?1")
	public Boat findBoatWithPhotos(int boatId);
	
	@Query("select boat from Boat boat join fetch boat.disconutOffers disconutOffers where boat.id=?1")
	public Boat findBoatWithDiscountOffers(int boatId);
	
	@Query("select boat from Boat boat where boat.id=?1")
	public Boat findBoatById(int boatId);
}
