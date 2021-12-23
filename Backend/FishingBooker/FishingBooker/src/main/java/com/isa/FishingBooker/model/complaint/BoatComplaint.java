package com.isa.FishingBooker.model.complaint;

import com.isa.FishingBooker.model.Boat;

public class BoatComplaint extends Complaint{
	
	private Boat boat;

	@Override
	public void setAppelleeId(int id) {
		this.boat=new Boat(id);
	}

	@Override
	public String getAppelleEmail() {
		return this.boat.getBoatOwner().getEmail();
	}
}
