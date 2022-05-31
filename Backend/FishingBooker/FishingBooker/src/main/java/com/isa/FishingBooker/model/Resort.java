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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.exceptions.InvalidNumberOfPersonsException;
import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.exceptions.ServicePriceAlreadyExistException;
import com.isa.FishingBooker.exceptions.UndefinedServicePricesException;

@Entity
public class Resort {
	@Id
	@Column(name="resort_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
	private Address resortAddress;
	@Column(name = "num_of_rooms")
	private int numOfRooms;
	@Column(name = "num_of_beds")
	private int numOfBeds;
	@Column(name = "rules")
	private String rules;
	@Column(name = "rate")
	private int rate;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String extrasServices;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<User> customers = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Photo> photos = new HashSet<Photo>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DiscountOffer> discountOffers = new HashSet<DiscountOffer>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ResortRentPrice> prices = new HashSet<ResortRentPrice>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Period> resortAvailable;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="resort_owner_id")
	private ResortOwner resortowner;
	
	
	public Resort() {}
	
	public Resort(Integer id) {
		this.id=id;
	}

	public Resort(String name, String description, Address resortAddress, int numOfRooms, int numOfBeds, String rules) {
		super();
		this.name = name;
		this.description = description;
		this.resortAddress = resortAddress;
		this.numOfRooms = numOfRooms;
		this.numOfBeds = numOfBeds;
		this.rules = rules;
	}
	
	public Resort updateInfo(Resort resort) {
		this.id=resort.getId();
		this.name = resort.getName();
		this.resortAddress = resort.getResortAddress();
		this.numOfRooms = resort.getNumOfRooms();
		this.numOfBeds = resort.getNumOfBeds();
		this.description = resort.getDescription();
		this.rules = resort.getRules();
		this.discountOffers = resort.getDiscountOffers();
		this.extrasServices = resort.getExtrasServices();
		this.rate = resort.getRate();
		this.status = resort.getStatus();
		this.customers = resort.getCustomers();
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
		return customers;
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

	public Set<DiscountOffer> getDiscountOffers() {
		return discountOffers;
	}

	public void setDiscountOffers(Set<DiscountOffer> discountOffers) {
		this.discountOffers = discountOffers;
	}

	public Set<ResortRentPrice> getPrices() {
		return prices;
	}

	public void setPrices(Set<ResortRentPrice> prices) {
		this.prices = prices;
	}

	public Set<Period> getResortAvailable() {
		return resortAvailable;
	}

	public void setResortAvailable(Set<Period> resortAvailable) {
		this.resortAvailable = resortAvailable;
	}
	
	@JsonIgnore
	public ResortOwner getResortOwner() {
		return resortowner;
	}

	public void setResortOwner(ResortOwner resortowner) {
		this.resortowner = resortowner;
	}
	
	public int getResortOwnerId() {
		return resortowner.getId();
	}
	
	public Resort addNewCustomer(User user) {
		this.customers.add(user);
		return this;
	}

	public Resort removeCustomer(User user) {
		this.customers.removeIf(subs -> subs.getId().equals(user.getId()));
		return this;
	}

	public double calculatePrice(int duration) {
		double resortRentPrice = 0;
		while (duration != 0) {
			ResortRentPrice price = getBestOfferByDuration(duration);
			if (price == null)
				throw new UndefinedServicePricesException();
			resortRentPrice += price.getPrice();
			duration -= price.getNumberOfDays();
		}
		return resortRentPrice;
	}

	private ResortRentPrice getBestOfferByDuration(int duration) {
		if (prices == null || prices.size() == 0)
			throw new UndefinedServicePricesException();
		ResortRentPrice ret = null;
		for (Object object : prices.stream().sorted().collect(Collectors.toList())) {
			ResortRentPrice price = (ResortRentPrice) object;
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
		if (discountOffers == null)
			discountOffers = new HashSet<>();
		discountOffers.add(offer);
	}
	
	public void addPrice(ResortRentPrice price) {
		if (prices == null)
			prices = new HashSet<ResortRentPrice>();
		checkSameNumberOfDaysPriceExist(price.getNumberOfDays());
		prices.add(price);
	}

	private void checkSameNumberOfDaysPriceExist(int numberOfDays) {
		if (prices.stream().filter(price -> price.getNumberOfDays() == numberOfDays).count() != 0)
			throw new ServicePriceAlreadyExistException();
	}


	
	public void updateStandardPeriod(Period takenPeriodOfAppointment) {
		for (Period resortPeriod : resortAvailable) {
			try {
				resortPeriod.overlap(takenPeriodOfAppointment);
			} catch (PeriodOverlapException ex) {
				if (Period.isSameDate(resortPeriod.getStartDate(), takenPeriodOfAppointment.getStartDate())) {
					resortPeriod.setStartDate(takenPeriodOfAppointment.getEndDate());
				} else {
					Period newBeforePeriod = new Period(resortPeriod.getStartDate(),
							takenPeriodOfAppointment.getStartDate());
					Period newAfterPeriod = new Period(takenPeriodOfAppointment.getEndDate(), resortPeriod.getEndDate());
					resortAvailable.remove(resortPeriod);
					resortAvailable.add(newBeforePeriod);
					resortAvailable.add(newAfterPeriod);
				}
				break;
			}
		}
	}
	
	public void addAvailablePeriod(Period period) {
		if (this.resortAvailable == null)
			this.resortAvailable = new HashSet<Period>();
		this.resortAvailable.add(period);
	}
	
	
	
	
	
	
	
	
}
