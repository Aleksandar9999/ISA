package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Extras {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String service;
	private String price;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="boat_id")
	private Boat boat;
   
   
   public Integer getId() {
	return id;
   }

   public void setId(Integer id) {
	this.id = id;
   }

   public String getService() {
      return service;
   }

   public void setService(String service) {
      this.service = service;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public Boat getBoat() {
	return boat;
   }

   public void setBoat(Boat boat) {
	this.boat = boat;
   }

   
   
}