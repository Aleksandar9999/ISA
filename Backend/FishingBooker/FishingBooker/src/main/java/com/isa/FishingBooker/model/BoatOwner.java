package com.isa.FishingBooker.model;

import java.util.ArrayList;
import java.util.List;

public class BoatOwner  extends User {
	private List<Boat> boats;
	
	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(Boat boat) {
		boats.add(boat);
	}

	public BoatOwner(){
		boats=new ArrayList<>();
	}
}
