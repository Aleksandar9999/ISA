package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DiscountOffer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="validity_per_id")
	private Period validityPeriod;
	@ManyToOne
	@JoinColumn(name="reservation_per_id")
	private Period reservationPeriod;
	private String place;
	private int maxPerson;
	private String additionalServices;
	private double price;
	@ManyToOne
	@JoinColumn(name="boat_id")
	private Boat boat;
   
   
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

}