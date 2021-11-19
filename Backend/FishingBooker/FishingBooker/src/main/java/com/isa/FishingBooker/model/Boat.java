package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Boat {
	
	private String name;
	private String type;
	private String length;
	private String engineId;
	private String enginePower;
	private int maxSpeed;
	private String navigationEquipment;
	@ManyToOne
	private Address boatAddress;
	private String description;
	private int maxPerson;
	private String rules;
	private String fishingEquipment;
	private double cancelPercentage;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Photo> photos;
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Extras> extrasServices;
	@OneToMany(fetch=FetchType.LAZY)
	private Set<DiscountOffer> disconutOffers;
	
	@ManyToOne
	private BoatOwner boatOwner;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	 
	 public Boat() {
		 
	 }

	 public Boat(String name, String type, String length, String engineId, String enginePower, int maxSpeed,
			String navigationEquipment, Address boatAddress, String description, int maxPerson,
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

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(String navigationEquipment) {
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

	public BoatOwner getBoatOwnerId() {
		return boatOwner;
	}

	public void setBoatOwnerId(BoatOwner boatOwnerId) {
		this.boatOwner = boatOwner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	 public void addPhoto(Photo photo) {
	      if (photos == null)
	         photos = new HashSet<>();
	      photos.add(photo);
	   }

	   public void addExtraService(Extras extras) {
	      if (extrasServices == null)
	         extrasServices = new HashSet<>();
	      extrasServices.add(extras);
	   }

	   public void addDiscountOffer(DiscountOffer offer) {
	      if (disconutOffers == null)
	         disconutOffers = new HashSet<>();
	      disconutOffers.add(offer);
	   }
	
	
}
