package com.isa.FishingBooker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Resort {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address resortAddress;
	private int numOfRooms;
	private int numOfBeds;
	private String rules;
	@OneToMany(fetch=FetchType.EAGER)
	private Set<Photo> photos;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Extras> extrasServices;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<DiscountOffer> disconutOffers;
	@ManyToOne
	@JoinColumn(name="resortOwner_id")
	private ResortOwner resortOwner;

	
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

	public ResortOwner getResortOwner() {
		return resortOwner;
	}

	public void setResortOwner(ResortOwner resortOwner) {
		this.resortOwner = resortOwner;
	}

	
	
	public Resort() {}
	
	
	
	
	
}
