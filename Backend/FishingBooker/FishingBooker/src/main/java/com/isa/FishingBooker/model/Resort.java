package com.isa.FishingBooker.model;

import java.util.Date;
import java.util.List;

public class Resort {
	
	private String name;
	private String description;
	private Address resortAddress;
	private int numOfRooms;
	private int numOfBeds;
	private String rules;


	private List<Photo> photos;
	private List<Extras> extrasServices;
	private List<DiscountOffer> disconutOffers;
	private String resortOwnerId;
	private String id;
	
	public Resort(String name, String description, Address resortAddress, int numOfRooms, int numOfBeds, String rules) {
		super();
		this.name = name;
		this.description = description;
		this.resortAddress = resortAddress;
		this.numOfRooms = numOfRooms;
		this.numOfBeds = numOfBeds;
		this.rules = rules;
	}
	
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

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Extras> getExtrasServices() {
		return extrasServices;
	}

	public void setExtrasServices(List<Extras> extrasServices) {
		this.extrasServices = extrasServices;
	}

	public List<DiscountOffer> getDisconutOffers() {
		return disconutOffers;
	}

	public void setDisconutOffers(List<DiscountOffer> disconutOffers) {
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
