package com.isa.FishingBooker.model.complaint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.isa.FishingBooker.model.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private User appellant;
	private String complaintText;
	private String adminResponse;
	public Complaint() {
	}
	
	public abstract void setAppelleeId(int id);
	//public abstract T getAppellee(int id);
	public abstract String getAppelleEmail();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminResponse() {
		return adminResponse;
	}

	public void setAdminResponse(String adminResponse) {
		this.adminResponse = adminResponse;
	}

	public User getAppellant() {
		return appellant;
	}

	public void setAppellant(User appellant) {
		this.appellant = appellant;
	}
	public void setAppellantId(int id) {
		this.appellant=new User(id);
	}

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaint) {
		this.complaintText = complaint;
	}

}
