package com.isa.FishingBooker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResortRentPrice implements Comparable<ResortRentPrice> {

	@Id
	@Column(name = "resortrent_price_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numberOfDays;
	private double price;
	
	public ResortRentPrice() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(ResortRentPrice p) {
		if(this.numberOfDays>p.getNumberOfDays()) {
			return 1;
		}else return -1;
	}
}
