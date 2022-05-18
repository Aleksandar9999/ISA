package com.isa.FishingBooker.dto;

import java.util.Date;

import com.isa.FishingBooker.model.Address;
import com.isa.FishingBooker.model.Appointment;

public class DiscountOfferDTO {
	private Integer appointmentId;
	private Date start;
	private Address address;
	private double price;
	private double newPrice;
	private double discount;
	
	public DiscountOfferDTO() {}
	
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
