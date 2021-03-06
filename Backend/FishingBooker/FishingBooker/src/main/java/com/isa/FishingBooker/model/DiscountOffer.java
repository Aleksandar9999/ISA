package com.isa.FishingBooker.model;

public class DiscountOffer {
   private Period validityPeriod;
   private Period reservationPeriod;
   private String place;
   private int maxPerson;
   private String additionalServices;
   private double price;

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