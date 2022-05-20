package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.exceptions.InvalidNumberOfPersonsException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.exceptions.ServicePriceAlreadyExistException;
import com.isa.FishingBooker.exceptions.UndefinedServicePricesException;

@Entity
@Transactional
public class Boat {
	@Id
	@Column(name="boat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "type_of_boat")
	private String typeOfBoat;
	@Column(name = "length")
	private String length;
	@Column(name = "engine_id")
	private String engineId;
	@Column(name = "engine_power")
	private String enginePower;
	@Column(name = "max_speed")
	private int maxSpeed;
	@Column(name = "navigation_equipment")
	private String navigationEquipment;
	@OneToOne(cascade = CascadeType.ALL)
	private Address boatAddress;
	@Column(name = "description")
	private String description;
	@Column(name = "max_person")
	private int maxPerson;
	@Column(name = "rules")
	private String rules;
	@Column(name = "fishing_equipment")
	private String fishingEquipment;
	@Column(name = "cancel_percentage")
	private double cancelPercentage;
	@Column(name = "rate")
	private int rate;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String extrasServices;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> customers = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Photo> photos = new HashSet<Photo>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DiscountOffer> disconutOffers = new HashSet<DiscountOffer>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatRentPrice> prices = new HashSet<BoatRentPrice>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Period> boatAvailable;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "boat_owner_id")
	private BoatOwner boatowner;


	public Boat() {

	}

	public Boat(Integer id) {
		this.id=id;
	}



	public Boat(String name, String typeOfBoat, String length, String engineId, String enginePower, int maxSpeed,
			String navigationEquipment, Address boatAddress, String description, int maxPerson, String rules,
			String fishingEquipment, double cancelPercentage) {
		this.name = name;
		this.typeOfBoat = typeOfBoat;
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
		this.cancelPercentage = cancelPercentage;
	}
	
	public Boat updateInfo(Boat boat) {
		this.name = boat.getName();
		this.typeOfBoat = boat.getTypeOfBoat();
		this.length = boat.getLength();
		this.engineId = boat.getEngineId();
		this.enginePower = boat.getEnginePower();
		this.maxSpeed = boat.getMaxSpeed();
		this.navigationEquipment = boat.getNavigationEquipment();
		this.boatAddress = boat.getBoatAddress();
		this.description = boat.getDescription();
		this.maxPerson = boat.getMaxPerson();
		this.rules = boat.getRules();
		this.fishingEquipment = boat.getFishingEquipment();
		this.cancelPercentage = boat.getCancelPercentage();
		this.disconutOffers = boat.getDisconutOffers();
		this.extrasServices = boat.getExtrasServices();
		this.rate = boat.getRate();
		this.status = boat.getStatus();
		this.customers = boat.getCustomers();
		return this;
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

	public String getTypeOfBoat() {
		return typeOfBoat;
	}

	public void setTypeOfBoat(String typeOfBoat) {
		this.typeOfBoat = typeOfBoat;
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getExtrasServices() {
		return extrasServices;
	}

	public void setExtrasServices(String extrasServices) {
		this.extrasServices = extrasServices;
	}

	public Set<User> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<User> customers) {
		this.customers = customers;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Set<DiscountOffer> getDisconutOffers() {
		return disconutOffers;
	}

	public void setDisconutOffers(Set<DiscountOffer> disconutOffers) {
		this.disconutOffers = disconutOffers;
	}

	public Set<BoatRentPrice> getPrices() {
		return prices;
	}

	public void setPrices(Set<BoatRentPrice> prices) {
		this.prices = prices;
	}
	
	@JsonIgnore
	public BoatOwner getBoatOwner() {
		return boatowner;
	}
	
	public int getBoatOwnerId() {
		return boatowner.getId();
	}

	public void setBoatOwner(BoatOwner boatowner) {
		this.boatowner = boatowner;
	}
	
	public Boat addNewCustomer(User user) {
		this.customers.add(user);
		return this;
	}

	public Boat removeCustomer(User user) {
		this.customers.removeIf(subs -> subs.getId().equals(user.getId()));
		return this;
	}

	public double calculatePrice(int duration) {
		double boatRentPrice = 0;
		while (duration != 0) {
			BoatRentPrice price = getBestOfferByDuration(duration);
			if (price == null)
				throw new UndefinedServicePricesException();
			boatRentPrice += price.getPrice();
			duration -= price.getNumberOfDays();
		}
		return boatRentPrice;
	}

	private BoatRentPrice getBestOfferByDuration(int duration) {
		if (prices == null || prices.size() == 0)
			throw new UndefinedServicePricesException();
		BoatRentPrice ret = null;
		for (Object object : prices.stream().sorted().collect(Collectors.toList())) {
			BoatRentPrice price = (BoatRentPrice) object;
			if (price.getNumberOfDays() <= duration)
				ret = price;
		}
		return ret;
	}
	
	public void addPhoto(Photo photo) {
		if (photos == null)
			photos = new HashSet<>();
		photos.add(photo);
	}

	public void deletePhoto(int id) {
		this.photos.removeIf(photo -> photo.getId() == id);
	}

	public void addDiscountOffer(DiscountOffer offer) {
		if (disconutOffers == null)
			disconutOffers = new HashSet<>();
		disconutOffers.add(offer);
	}
	
	public void addPrice(BoatRentPrice price) {
		if (prices == null)
			prices = new HashSet<BoatRentPrice>();
		checkSameNumberOfDaysPriceExist(price.getNumberOfDays());
		prices.add(price);
	}

	private void checkSameNumberOfDaysPriceExist(int numberOfDays) {
		if (prices.stream().filter(price -> price.getNumberOfDays() == numberOfDays).count() != 0)
			throw new ServicePriceAlreadyExistException();
	}

	public void validateMaxNumberOfPersons(int number) {
		if (number > this.maxPerson || number <= 0)
			throw new InvalidNumberOfPersonsException();
	}
	
	public void updateStandardPeriod(Period takenPeriodOfAppointment) {
		for (Period boatOwnerPeriod : boatAvailable) {
			try {
				boatOwnerPeriod.overlap(takenPeriodOfAppointment);
			} catch (PeriodOverlapException ex) {
				if (Period.isSameDate(boatOwnerPeriod.getStartDate(), takenPeriodOfAppointment.getStartDate())) {
					boatOwnerPeriod.setStartDate(takenPeriodOfAppointment.getEndDate());
				} else {
					Period newBeforePeriod = new Period(boatOwnerPeriod.getStartDate(),
							takenPeriodOfAppointment.getStartDate());
					Period newAfterPeriod = new Period(takenPeriodOfAppointment.getEndDate(), boatOwnerPeriod.getEndDate());
					boatAvailable.remove(boatOwnerPeriod);
					boatAvailable.add(newBeforePeriod);
					boatAvailable.add(newAfterPeriod);
				}
				break;
			}
		}
	}
	
	public Set<Period> getAvailable() {
		return boatAvailable;
	}

	public void setAvailable(Set<Period> available) {
		this.boatAvailable = available;
	}

	public void addAvailablePeriod(Period period) {
		if (this.boatAvailable == null)
			this.boatAvailable = new HashSet<Period>();
		this.boatAvailable.add(period);
	}


	

	
}
