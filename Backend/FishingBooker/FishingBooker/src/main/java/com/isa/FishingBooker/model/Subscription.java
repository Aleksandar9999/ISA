package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

@Entity
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Boat> boats;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Resort> resorts;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<TutorService> tutorServices;

	public Subscription() {
	}
	
	public Subscription(User usr) {
		this.user=usr;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Boat> getBoats() {
		if(boats==null) return new HashSet<Boat>();
		return boats;
	}
	public void setBoats(Set<Boat> boats) {
		this.boats = boats;
	}
	public Set<Resort> getResorts() {
		return resorts;
	}
	public void setResorts(Set<Resort> resorts) {
		this.resorts = resorts;
	}
	public Set<TutorService> getTutorServices() {
		if(tutorServices==null) return new HashSet<TutorService>();
		return tutorServices;
	}
	public void setTutorServices(Set<TutorService> tutorServices) {
		this.tutorServices = tutorServices;
	}

	public void addTutorService(TutorService tutorService) {
		if(this.tutorServices==null) this.tutorServices=new HashSet<TutorService>();
		this.tutorServices.add(tutorService);
	}
	
	public void addBoat(Boat boat) {
		if(this.boats==null) this.boats=new HashSet<Boat>();
		this.boats.add(boat);
	}
}
