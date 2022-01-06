package com.isa.FishingBooker.model.complaint;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.Boat;

@Entity
public class BoatComplaint extends Complaint<Boat>{
	@OneToOne
	private Boat boat;

	@Override
	public void setAppelleeService(Boat item) {
		this.boat=boat;
	}

	@Override
	public void setAppelleeServiceId(int id) {
		this.boat=new Boat(id);
	}

	public int getAppelleServiceId() {
		return this.boat.getId();
	}

	@Override
	public String getType() {
		return "boat";
	}
}
