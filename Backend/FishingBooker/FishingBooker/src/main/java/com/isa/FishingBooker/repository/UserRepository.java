package com.isa.FishingBooker.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User ,Integer> {
	
	public User findByEmail(String email);
	@Query("select tutor from Tutor tutor join fetch tutor.services services where tutor.id=?1")
	public Tutor findTutorWithServices(int tutorId);
	
	@Query("select u from User u where u.status=PENDING")
	public List<User> findPendingUsers();
	
}
