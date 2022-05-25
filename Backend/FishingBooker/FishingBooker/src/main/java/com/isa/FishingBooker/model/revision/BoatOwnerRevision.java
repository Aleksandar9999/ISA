package com.isa.FishingBooker.model.revision;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.Tutor;

@Entity
public class BoatOwnerRevision extends Revision {

	@OneToOne
	private BoatOwner boatowner;
	
	public BoatOwnerRevision() {
	}
	
	@Override
	public String getOwnerEmail() {
	return boatowner.getEmail();
	}

	@Override
	public void setRelatedId(int id) {
		this.boatowner = new BoatOwner(id);
		
	}

	@Override
	public int getRelatedId() {
		return this.boatowner.getId();
	}

}
