package com.isa.FishingBooker.repository;



import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.ResortOwner;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User ,Integer> {
	
	public User findByEmail(String email);
	
	@Query("select tutor from Tutor tutor join fetch tutor.services services where tutor.id=?1")
	public Tutor findTutorWithServices(int tutorId);
	
	@Query("select boatowner from BoatOwner boatowner join fetch boatowner.boats boats where boatowner.id=?1")
	public BoatOwner findBoatOwnerWithBoats(int boatownerId);
	
	@Query("select resortowner from ResortOwner resortowner join fetch resortowner.resorts resorts where resortowner.id=?1")
	public ResortOwner findResortOwnerWithResorts(int resortownerId);
	
	@Query("select u from User u where u.status=?1")
	public List<User> findUsersByProfileStatus(Status status);
	
	@Query("select u from User u where u.status=?1")
	public List<User> search(Status status);
	
	@Query("select u from User u where TYPE(u)=User")
	public List<User> findAllClients();
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public User save(User user);
	
}
