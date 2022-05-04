package com.isa.FishingBooker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PointsSettings{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double pointsForClients;
	private double pointsForUsers;
	@OneToOne(cascade = CascadeType.ALL)
	private Period valid;
	@OneToOne
	private Admin creator;

	public PointsSettings() {
	}

	public double getPointsForClients() {
		return pointsForClients;
	}

	public void setPointsForClients(double pointsForClients) {
		this.pointsForClients = pointsForClients;
	}

	public double getPointsForUsers() {
		return pointsForUsers;
	}

	public void setPointsForUsers(double pointsForUsers) {
		this.pointsForUsers = pointsForUsers;
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

}
