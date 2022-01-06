package com.isa.FishingBooker.model.complaint;

import javax.persistence.Entity;

import com.isa.FishingBooker.model.Tutor;
@Entity
public class TutorComplaint extends Complaint{
	
	public TutorComplaint() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setAppelleeService(Object item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAppelleeServiceId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAppelleServiceId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		return "tutor";
	}
}
