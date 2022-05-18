package com.isa.FishingBooker.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SystemData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double procentage;
	@ManyToOne(cascade = CascadeType.ALL)
	private Period valid;

	public void setEndDate(Timestamp end) {
		this.valid.setEndDate(end);
	}
	public void setStartDate(Timestamp start) {
		this.valid.setStartDate(start);
	}
	public SystemData() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Period getValid() {
		return valid;
	}

	public void setValid(Period valid) {
		this.valid = valid;
	}

	public double getProcentage() {
		return procentage;
	}

	public void setProcentage(double procentage) {
		this.procentage = procentage;
	}

}
