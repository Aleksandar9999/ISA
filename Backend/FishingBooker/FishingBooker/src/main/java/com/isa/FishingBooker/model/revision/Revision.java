package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "revisions")
public abstract class Revision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double rate;
	private String comment;
	private Status status;
	@OneToOne
	private User creator;
	@OneToOne
	private Admin adminResponded;
	
	public Revision() {
	}

	@JsonCreator
	public Revision(@JsonProperty("id") Integer id, @JsonProperty("rate") double rate,
			@JsonProperty("comment") String comment, @JsonProperty("status") Status status) {
		this.id = id;
		this.rate = rate;
		this.comment = comment;
		this.status = status;
	}

	public abstract String getOwnerEmail();

	public abstract void setRelatedId(int id);

	public abstract int getRelatedId();

	public Admin getAdminResponded() {
		return adminResponded;
	}

	public void setAdminResponded(Admin adminResponded) {
		this.adminResponded = adminResponded;
	}

	public Status getStatus() {
		return status;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public double getRate() {
		return rate;
	}

	public String getComment() {
		return comment;
	}
}
