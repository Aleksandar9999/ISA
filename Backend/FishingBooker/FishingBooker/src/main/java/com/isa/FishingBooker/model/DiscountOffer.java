package com.isa.FishingBooker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DiscountOffer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL)
	private Period validityPeriod;
	@OneToOne(cascade = CascadeType.ALL)
	private Period reservationPeriod;
	//private Address address;//U servisu postoji adresa odrzavanja?
	//private int maxPerson;
	//private String additionalServices;
	private double price;
	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
	//TODO:FIX THIS
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "tutorservice_id", referencedColumnName = "tutor_service_id")
	private TutorService tutorService;
	
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

   /*public String getPlace() {
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
*/
   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

}