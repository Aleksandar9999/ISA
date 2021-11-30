package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TutorService {
	@Id
	@Column(name = "tutor_service_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private int maxPerson;
	private String rules;
	private String fishingEquipment;
	private double cancelProcentage;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tutorService")
	private Set<Photo> photos = new HashSet<Photo>();
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Extras> extrasServices;

	@OneToMany(mappedBy = "tutorService", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DiscountOffer> disconutOffers = new HashSet<DiscountOffer>();
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

	public TutorService() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TutorService(String name, String description, int maxPerson, String rules, String fishingEquipment,
			double cancelProcentage, Address address) {
		this.name = name;
		this.description = description;
		this.maxPerson = maxPerson;
		this.rules = rules;
		this.fishingEquipment = fishingEquipment;
		this.cancelProcentage = cancelProcentage;
		this.address = address;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public Set<Extras> getExtrasServices() {
		return extrasServices;
	}

	public Set<DiscountOffer> getDisconutOffers() {
		return disconutOffers;
	}

	public void addPhoto(Photo photo) {
		photo.setTutorService(this);
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
		offer.setTutorService(this);
		try {
			disconutOffers.add(offer);
		} catch (Exception e) {
			disconutOffers = new HashSet<>();
			disconutOffers.add(offer);
		}
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

	public double getCancelProcentage() {
		return cancelProcentage;
	}

	public void setCancelProcentage(double cancelProcentage) {
		this.cancelProcentage = cancelProcentage;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	void setExtrasServices(Set<Extras> extrasServices) {
		this.extrasServices = extrasServices;
	}

	public void setDisconutOffers(Set<DiscountOffer> disconutOffers) {
		this.disconutOffers = disconutOffers;
	}

}