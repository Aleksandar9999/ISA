package com.isa.FishingBooker.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.User;
@Repository
public interface UserRepository extends JpaRepository<User ,Integer> {
	
	public User findByEmail(String email);
}
