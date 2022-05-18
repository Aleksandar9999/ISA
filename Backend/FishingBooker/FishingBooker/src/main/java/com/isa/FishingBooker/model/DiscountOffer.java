package com.isa.FishingBooker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DiscountOffer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="validity_per_id")
	private Period validityPeriod;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="reservation_per_id")
	private Period reservationPeriod;
	private String place;
	private int maxPerson;
	private String additionalServices;
	private double price;
	@Enumerated(EnumType.STRING)
	private AppointmentType entityType;
	
	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
	
	@ManyToOne
	@JoinColumn(name="tutor_service_id")
	private TutorService tutorService;
	
	@ManyToOne
	@JoinColumn(name="resort_id")
	private Resort resort;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
   
   public Integer getId() {
	return id;
   }

   public void setId(Integer id) {
	this.id = id;
   }

   public Period getValidityPeriod() {
      return validityPeriod;
   }

   public void setValidityPeriod(Period validityPeriod) {
      this.validityPeriod = validityPeriod;
   }

   public Period getReservationPeriod() {
      return reservationPeriod;
   }

   public void setReservationPeriod(Period reservationPeriod) {
      this.reservationPeriod = reservationPeriod;
   }

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public int getMaxPerson() {
      return maxPerson;
   }

   public void setMaxPerson(int maxPerson) {
      this.maxPerson = maxPerson;
   }

   public String getAdditionalServices() {
      return additionalServices;
   }

   public void setAdditionalServices(String additionalServices) {
      this.additionalServices = additionalServices;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

	public Boat getBoat() {
		return boat;
	}
	
	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	
	public TutorService getTutorService() {
		return tutorService;
	}
	
	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}
	
	public Resort getResort() {
		return resort;
	}
	
	public void setResort(Resort resort) {
		this.resort = resort;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppointmentType getEntityType() {
		return entityType;
	}

	public void setEntityType(AppointmentType entityType) {
		this.entityType = entityType;
	}
    
	

}