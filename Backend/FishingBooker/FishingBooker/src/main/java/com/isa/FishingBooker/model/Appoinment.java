package com.isa.FishingBooker.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appoinment {
	
   private Date start;
   private double duration;
   private int maxPerson;
   private String additionalServices;
   private double price;
   
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="address_id")
   private Address address;

   public Date getStart() {
      return start;
   }

   public void setStart(Date start) {
      this.start = start;
   }

   public double getDuration() {
      return duration;
   }

   public void setDuration(double duration) {
      this.duration = duration;
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

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   /*
    * public java.util.Collection<Extras> extras;
    * 
    * public java.util.Collection<Extras> getExtras() { if (extras == null) extras
    * = new java.util.HashSet<Extras>(); return extras; }
    * 
    * public java.util.Iterator getIteratorExtras() { if (extras == null) extras =
    * new java.util.HashSet<Extras>(); return extras.iterator(); }
    * 
    * public void setExtras(java.util.Collection<Extras> newExtras) {
    * removeAllExtras(); for (java.util.Iterator iter = newExtras.iterator();
    * iter.hasNext();) addExtras((Extras) iter.next()); }
    * 
    * public void addExtras(Extras newExtras) { if (newExtras == null) return; if
    * (this.extras == null) this.extras = new java.util.HashSet<Extras>(); if
    * (!this.extras.contains(newExtras)) this.extras.add(newExtras); }
    * 
    * public void removeExtras(Extras oldExtras) { if (oldExtras == null) return;
    * if (this.extras != null) if (this.extras.contains(oldExtras))
    * this.extras.remove(oldExtras); }
    * 
    * public void removeAllExtras() { if (extras != null) extras.clear(); }
    */
}