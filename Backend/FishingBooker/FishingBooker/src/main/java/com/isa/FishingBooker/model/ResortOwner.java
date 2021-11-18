package com.isa.FishingBooker.model;

import java.util.ArrayList;
import java.util.List;

public class ResortOwner extends User {
	private List<Resort> resorts;
	public List<Resort> getResorts() {
		return resorts;
	}

	public void setResorts(Resort resort) {
		resorts.add(resort);
	}

	public ResortOwner(){
		resorts=new ArrayList<>();
	}
}
