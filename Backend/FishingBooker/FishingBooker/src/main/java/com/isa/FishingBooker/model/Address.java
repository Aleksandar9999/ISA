package com.isa.FishingBooker.model;

public class Address {
   private Integer id;
   private String street;
   private String country;
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