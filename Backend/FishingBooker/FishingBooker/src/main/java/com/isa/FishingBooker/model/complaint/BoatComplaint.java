package com.isa.FishingBooker.model.complaint;

<<<<<<< HEAD
import com.isa.FishingBooker.model.Boat;

public class BoatComplaint extends Complaint{
	
	private Boat boat;

	@Override
	public void setAppelleeId(int id) {
=======
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
>>>>>>> b2710914ae44248c25812debfd4f88d13f48606b
		this.boat=new Boat(id);
	}

	@Override
<<<<<<< HEAD
	public String getAppelleEmail() {
		return this.boat.getBoatOwner().getEmail();
=======
	public int getAppelleServiceId() {
		return this.boat.getId();
	}

	@Override
	public String getType() {
		return "boat";
>>>>>>> b2710914ae44248c25812debfd4f88d13f48606b
	}
}
