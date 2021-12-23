package com.isa.FishingBooker.model.complaint;

import javax.persistence.Entity;

import com.isa.FishingBooker.model.Tutor;
@Entity
public class TutorComplaint extends Complaint{

	private Tutor appellee;
	
	public TutorComplaint() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getAppelleEmail() {
		return appellee.getEmail();
	}
	@Override
	public void setAppelleeId(int id) {
		appellee = new Tutor(id);
	}

	public Tutor getAppellee() {
		return appellee;
	}

	public void setAppellee(Tutor appellee) {
		this.appellee = appellee;
	}
}
