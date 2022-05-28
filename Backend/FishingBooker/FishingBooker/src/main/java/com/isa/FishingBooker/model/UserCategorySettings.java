package com.isa.FishingBooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCategorySettings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double discountProcentage;
	private double revenueProcentage;
	private double pointsMargine;
	public UserCategorySettings() {
		// TODO Auto-generated constructor stub
	}
	public void update(UserCategorySettings newItem) {
		this.discountProcentage=newItem.discountProcentage;
		this.revenueProcentage=newItem.revenueProcentage;
		this.pointsMargine=newItem.revenueProcentage;
	}

	public double getSystemRevenueProcentage() {
		return 100-revenueProcentage;
	}

	public double getPointsMargine() {
		return pointsMargine;
	}

	public void setPointsMargine(double pointsMargine) {
		this.pointsMargine = pointsMargine;
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
	public double getDiscountProcentage() {
		return discountProcentage;
	}
	public void setDiscountProcentage(double discountProcentage) {
		this.discountProcentage = discountProcentage;
	}
	public double getRevenueProcentage() {
		return revenueProcentage;
	}
	public void setRevenueProcentage(double revenueProcentage) {
		this.revenueProcentage = revenueProcentage;
	}
	
	
}
