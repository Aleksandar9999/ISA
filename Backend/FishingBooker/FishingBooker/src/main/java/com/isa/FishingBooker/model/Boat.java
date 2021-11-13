package com.isa.FishingBooker.model;

import java.util.ArrayList;
import java.util.List;

public class Boat {
	
	private String name;
	private String type;
	private String length;
	private String engineId;
	private String enginePower;
	private int maxSpeed;
	private NavigationEquipment navigationEquipment;
	private Address boatAddress;
	private String description;
	private int maxPerson;
	private String rules;
	private String fishingEquipment;
	private double cancelPercentage;
	
	private List<Extras> extrasServices;
	private List<DiscountOffer> disconutOffers;
	private String boatOwnerId;
	private String id;
	 
	 public Boat() {
		 
	 }

	 public Boat(String name, String type, String length, String engineId, String enginePower, int maxSpeed,
			NavigationEquipment navigationEquipment, Address boatAddress, String description, int maxPerson,
			String rules, String fishingEquipment) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.engineId = engineId;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.navigationEquipment = navigationEquipment;
		this.boatAddress = boatAddress;
		this.description = description;
		this.maxPerson = maxPerson;
		this.rules = rules;
		this.fishingEquipment = fishingEquipment;
	}

	private List<Photo> photos;
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public String getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public NavigationEquipment getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(NavigationEquipment navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public Address getBoatAddress() {
		return boatAddress;
	}

	public void setBoatAddress(Address boatAddress) {
		this.boatAddress = boatAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public double getCancelPercentage() {
		return cancelPercentage;
	}

	public void setCancelPercentage(double cancelPercentage) {
		this.cancelPercentage = cancelPercentage;
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

	public String getBoatOwnerId() {
		return boatOwnerId;
	}

	public void setBoatOwnerId(String boatOwnerId) {
		this.boatOwnerId = boatOwnerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	 public void addPhoto(Photo photo) {
	      if (photos == null)
	         photos = new ArrayList<>();
	      photos.add(photo);
	   }

	   public void addExtraService(Extras extras) {
	      if (extrasServices == null)
	         extrasServices = new ArrayList<>();
	      extrasServices.add(extras);
	   }

	   public void addDiscountOffer(DiscountOffer offer) {
	      if (disconutOffers == null)
	         disconutOffers = new ArrayList<>();
	      disconutOffers.add(offer);
	   }
	
	
}
