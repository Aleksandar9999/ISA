package com.isa.FishingBooker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.TutorService;
@Repository
public interface TutorServiceRepository extends JpaRepository<TutorService ,Integer> {

	@Query("select tutorService from TutorService tutorService join fetch tutorService.photos photos where tutorService.id=?1")
	public TutorService findTutorServiceWithPhotos(int tutorServiceId);
	
	@Query("select tutorService from TutorService tutorService join fetch tutorService.disconutOffers disconutOffers where tutorService.id=?1")
	public TutorService findTutorServiceWithDiscountOffers(int tutorServiceId);
}
