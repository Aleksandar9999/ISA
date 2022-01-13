package com.isa.FishingBooker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{
	@Query("select s from Subscription s where s.user.id=?1")
	public Subscription getByUserId(int userId);
}
