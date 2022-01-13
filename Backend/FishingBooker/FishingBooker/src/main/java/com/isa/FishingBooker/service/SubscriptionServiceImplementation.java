package com.isa.FishingBooker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.Resort;
import com.isa.FishingBooker.model.Subscription;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.SubscriptionRepository;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

	@Autowired
	SubscriptionRepository repository;
	@Override
	public void addNew(Subscription item) {
		// TODO Auto-generated method stub
		repository.save(item);
	}

	@Override
	public List<Subscription> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Subscription getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Subscription getByUserId(Integer id) {
		// TODO Auto-generated method stub
		return repository.getByUserId(id);
	}

	public String subscribeBoat(Boat boat) {
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		if(this.getByUserId(usr.getId())==null) {
			Subscription s = new Subscription();
			s.setUser(usr);
			Set<Boat> listBoats = new HashSet<Boat>();
			listBoats.add(boat);
			s.setBoats(listBoats);
			repository.save(s);
			return "Success";
		} else {
			Subscription s = this.getByUserId(usr.getId());
			if(s.getBoats()==null) {
				Set<Boat> listBoats = new HashSet<Boat>();
				listBoats.add(boat);
				s.setBoats(listBoats);
			} else {
				s.getBoats().add(boat);
			}	
			repository.save(s);
			return "Success";
		}		
	}
	
	public String subscribeResort(Resort resort) {
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		
		if(this.getByUserId(usr.getId())==null) {
			Subscription s = new Subscription();
			s.setUser(usr);
			Set<Resort> listResorts = new HashSet<Resort>();
			listResorts.add(resort);
			s.setResorts(listResorts);
			repository.save(s);
			return "Success";
		} else {
			Subscription s = this.getByUserId(usr.getId());
			if(s.getResorts()==null) {
				Set<Resort> listResorts = new HashSet<Resort>();
				listResorts.add(resort);
				s.setResorts(listResorts);
			} else {
				s.getResorts().add(resort);
			}	
			repository.save(s);
			return "Success";
		}
	}

	public String subscribeTutorService(TutorService tutorService) {
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		
		if(this.getByUserId(usr.getId())==null) {
			Subscription s = new Subscription();
			s.setUser(usr);
			Set<TutorService> listTutorServices = new HashSet<TutorService>();
			listTutorServices.add(tutorService);
			s.setTutorServices(listTutorServices);
			repository.save(s);
			return "Success";
		} else {
			Subscription s = this.getByUserId(usr.getId());
			if(s.getTutorServices()==null) {
				Set<TutorService> listTutorServices = new HashSet<TutorService>();
				listTutorServices.add(tutorService);
				s.setTutorServices(listTutorServices);
			} else {
				s.getTutorServices().add(tutorService);
			}	
			repository.save(s);
			return "Success";
		}
	}
	
	@Override
	public void update(Subscription item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cancelBoatSubscription(Boat boat) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		Subscription s = this.getByUserId(usr.getId());
		for(Boat b : s.getBoats()) {
			if(b.getId()==boat.getId()) {
				Set<Boat>boats=s.getBoats();
				boats.remove(b);
				s.setBoats(boats);
				repository.save(s);
				return null;
			}
		}
		return null;
	}

	@Override
	public String cancelResortSubscritpion(Resort resort) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		Subscription s = this.getByUserId(usr.getId());
		for(Resort r : s.getResorts()) {
			if(r.getId()==resort.getId()) {
				Set<Resort> resorts=s.getResorts();
				resorts.remove(r);
				s.setResorts(resorts);
				repository.save(s);
				return null;
			}
		}
		return null;
	}

	@Override
	public String cancelAdventureSubscription(TutorService tutorService) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		Subscription s = this.getByUserId(usr.getId());
		for(TutorService ts : s.getTutorServices()) {
			if(ts.getId()==tutorService.getId()) {
				Set<TutorService> tss=s.getTutorServices();
				tss.remove(ts);
				s.setTutorServices(tss);
				repository.save(s);
				return null;
			}
		}
		return null;
	}
	
	public Subscription getSubscriptionForUser() {
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		return this.getByUserId(usr.getId());
	}

}
