package com.isa.FishingBooker.model;

import java.util.Set;

public class Resort {
	
	private Integer id;
	private String name;
	private String description;
	private Address resortAddress;
	private int numOfRooms;
	private int numOfBeds;
	private String rules;
	private Set<Photo> photos;
	private Set<Extras> extrasServices;
	private Set<DiscountOffer> disconutOffers;
	private String resortOwnerId;

	
	public Resort(String name, String description, Address resortAddress, int numOfRooms, int numOfBeds, String rules) {
		super();
		this.name = name;
		this.description = description;
		this.resortAddress = resortAddress;
		this.numOfRooms = numOfRooms;
		this.numOfBeds = numOfBeds;
		this.rules = rules;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getResortAddress() {
		return resortAddress;
	}

	public void setResortAddress(Address resortAddress) {
		this.resortAddress = resortAddress;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Set<Extras> getExtrasServices() {
		return extrasServices;
	}

	public void setExtrasServices(Set<Extras> extrasServices) {
		this.extrasServices = extrasServices;
	}

	public Set<DiscountOffer> getDisconutOffers() {
		return disconutOffers;
	}

	public void setDisconutOffers(Set<DiscountOffer> disconutOffers) {
		this.disconutOffers = disconutOffers;
	}

	public String getResortOwnerId() {
		return resortOwnerId;
	}

	public void setResortOwnerId(String resortOwnerId) {
		this.resortOwnerId = resortOwnerId;
	}

	
	
	public Resort() {}
	
	
	
	
	
}
