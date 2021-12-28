package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.Subscription;
import com.isa.FishingBooker.model.TutorService;

public interface SubscriptionService extends Service<Subscription> {
	public Subscription getByUserId(Integer id); 
	public String subscribeBoat(Boat boat);
	public String subscribeResort(Resort resort);
	public String subscribeTutorService(TutorService tutorService);
	public String cancelBoatSubscription(Boat boat);
	public String cancelResortSubscritpion(Resort resort);
	public String cancelAdventureSubscription(TutorService tutorService);
	public Subscription getSubscriptionForUser();
}
