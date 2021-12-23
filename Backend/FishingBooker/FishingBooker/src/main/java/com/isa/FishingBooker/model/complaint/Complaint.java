package com.isa.FishingBooker.model.complaint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Complaint<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private User appellant;
	@OneToOne
	private User appellee;
	private String complaintText;
	private String adminResponse;

	public Complaint() {
	}
	
	public abstract void setAppelleeService(T item);
	public abstract int getAppelleServiceId();
	public abstract void setAppelleeServiceId(int id);
	
	public int getAppelleeId() {return this.appellee.getId();}
	public String getAppelleeEmail() {return this.appellee.getEmail();}
	public int getAppellantId() {return this.appellant.getId();}
	public String getAppellantEmail() {return this.appellant.getEmail();}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAppellant(User appellant) {
		this.appellant = appellant;
	}

	public void setAppellee(User appellee) {
		this.appellee = appellee;
	}

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}

	public String getAdminResponse() {
		return adminResponse;
	}

	public void setAdminResponse(String adminResponse) {
		this.adminResponse = adminResponse;
	}

	public void setAppellantId(int appellantId) {
		this.appellant=new User(appellantId);
		
	}

	public void setAppelleeId(int appelleeId) {
		this.appellee=new User(appelleeId);		
	}
}
