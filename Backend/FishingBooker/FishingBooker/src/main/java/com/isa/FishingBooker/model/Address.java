package com.isa.FishingBooker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String street;
	@Column(nullable=false)
	private String country;
	@Column(nullable=false)
	private String city;
	private double lat;
	private double lng;
   
   
   
   public Integer getId() {
	return id;
   }

   public void setId(Integer id) {
	this.id = id;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public double getLat() {
      return lat;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }

   public double getLng() {
      return lng;
   }

   public void setLng(double lng) {
      this.lng = lng;
   }

}