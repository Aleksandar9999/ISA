package com.isa.FishingBooker.model;

import java.util.Date;
import java.util.List;

public class Resort {
	private String id;
	private String name;
	private Address address;
	private String prom;
	private String images;
	private String numOfRooms;
	private String numOfBeds;
	private List<Date> freeTerms;
	private List<String> rules;
	private List<String> prices;
	
	public Resort() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getProm() {
		return prom;
	}
	public void setProm(String prom) {
		this.prom = prom;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(String numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public String getNumOfBeds() {
		return numOfBeds;
	}
	public void setNumOfBeds(String numOfBeds) {
		this.numOfBeds = numOfBeds;
	}
	public List<Date> getFreeTerms() {
		return freeTerms;
	}
	public void setFreeTerms(List<Date> freeTerms) {
		this.freeTerms = freeTerms;
	}
	public List<String> getRules() {
		return rules;
	}
	public void setRules(List<String> rules) {
		this.rules = rules;
	}
	public List<String> getPrices() {
		return prices;
	}
	public void setPrices(List<String> prices) {
		this.prices = prices;
	}
	
	
	
}
