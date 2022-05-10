package com.isa.FishingBooker.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PointsSettings{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private double pointsForClients;
	@Column(nullable = false)
	private double penalty;
	@Column(nullable = false)
	private double pointsForOwners;
	@OneToOne(cascade = CascadeType.ALL)
	private Period valid;
	@OneToOne
	private Admin creator;

	public PointsSettings() {
	}
	
	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public double getPointsForOwners() {
		return pointsForOwners;
	}

	public void setPointsForOwners(double pointsForOwners) {
		this.pointsForOwners = pointsForOwners;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPointsForClients() {
		return pointsForClients;
	}

	public void setPointsForClients(double pointsForClients) {
		this.pointsForClients = pointsForClients;
	}

	public Period getValid() {
		return valid;
	}

	public void setValid(Period validPeriod) {
		this.valid = validPeriod;
	}

	public Admin getCreator() {
		return creator;
	}

	public void setCreator(Admin creator) {
		this.creator = creator;
	}
	
	public void setEndDate(Timestamp timestamp) {this.valid.setEndDate(timestamp);}
	public void setStartDate(Timestamp timestamp) {this.valid.setStartDate(timestamp);}
}
