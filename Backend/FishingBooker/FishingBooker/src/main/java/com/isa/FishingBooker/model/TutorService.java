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
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.exceptions.UndefinedServicePricesException;

@Entity
@Transactional
public class TutorService {
	@Id
	@Column(name = "tutor_service_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "max_person")
	private int maxPerson;
	@Column(name = "rules")
	private String rules;
	@Column(name = "fishing_equipment")
	private String fishingEquipment;
	@Column(name = "cancel_procentage")
	private double cancelProcentage;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@Column(name = "rate")
	private int rate;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	private String tutorBio;
	//@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private String extrasServices;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> subscribers = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Photo> photos = new HashSet<Photo>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DiscountOffer> disconutOffers = new HashSet<DiscountOffer>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Period> standardPeriods = new HashSet<Period>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ServicePrice> prices = new HashSet<ServicePrice>();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;
	
	public TutorService() {
	}
	public TutorService(Integer id) {
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

	public TutorService updateInfo(TutorService service) {
		this.name = service.getName();
		this.description = service.getDescription();
		this.maxPerson = service.getMaxPerson();
		this.address = service.getAddress();
		this.cancelProcentage = service.getCancelProcentage();
		this.rules=service.getRules();
		this.fishingEquipment=service.getFishingEquipment();
		this.rate=service.getRate();
		this.status=service.getStatus();
		this.tutorBio=service.getTutorBio();
		this.extrasServices=service.getExtrasServices();
		return this;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}
	
	public Set<User> getSubscribers() {
		return Set.copyOf(this.subscribers);
	}
	
	public TutorService addNewSubscriber(User user) {
		if(this.subscribers==null) this.subscribers=new HashSet<User>();
		this.subscribers.add(user);
		return this;
	}

	public double calculatePrice(int duration) {
		double appointmentPrice=0;
		while (duration != 0) {
			ServicePrice price = getBestOfferByDuration(duration);
			appointmentPrice+=price.getPrice();
			duration-=price.getNumberOfDays();
		}
		return appointmentPrice;
	}

	private ServicePrice getBestOfferByDuration(int duration) {
		if(prices == null || prices.size()==0) throw new UndefinedServicePricesException();
		ServicePrice ret = null;
		for (Object object : prices.stream().sorted().collect(Collectors.toList())) {
			ServicePrice price=(ServicePrice)object;		
			if(price.getNumberOfDays()<=duration)
				ret=price;
		}
		return ret;
	}
	
	public void addStandardPeriod(Period period) {
		if(this.standardPeriods==null) this.standardPeriods=new HashSet<Period>();
		this.standardPeriods.add(period);
	}
	
	public Set<Period> getStandardPeriods(){
		return this.standardPeriods;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
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

	public Set<DiscountOffer> getDisconutOffers() {
		return disconutOffers;
	}

	public void addPhoto(Photo photo) {
		if (photos == null)
			photos = new HashSet<>();
		photos.add(photo);
	}

	public void deletePhoto(int id) {
		this.photos.removeIf(photo->photo.getId()==id);
	}

	public void addDiscountOffer(DiscountOffer offer) {
		if (disconutOffers == null)
			disconutOffers = new HashSet<>();
		disconutOffers.add(offer);
	}

	public Set<ServicePrice> getPrices() {
		return prices;
	}

	public void addPrice(ServicePrice price) {
		if (prices == null)
			prices = new HashSet<ServicePrice>();
		prices.add(price);
	}

	public void setPrices(Set<ServicePrice> prices) {
		this.prices = prices;
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

	public void setDisconutOffers(Set<DiscountOffer> disconutOffers) {
		this.disconutOffers = disconutOffers;
	}

	public String getTutorBio() {
		return tutorBio;
	}
	public void setTutorBio(String tutorBio) {
		this.tutorBio = tutorBio;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	public void setStandardPeriods(Set<Period> standardPeriods) {
		this.standardPeriods = standardPeriods;
	}
	public String getExtrasServices() {
		return extrasServices;
	}
	public void setExtrasServices(String extrasServices) {
		this.extrasServices = extrasServices;
	}
	
}