package com.isa.FishingBooker.model;

import java.util.ArrayList;
import java.util.List;

public class BoatOwner  extends User {
	private List<BoatOwnerServices> services;
	
	public List<BoatOwnerServices> getServices() {
		return services;
	}

	public void setServices(BoatOwnerServices service) {
		services.add(service);
	}

	public BoatOwner(){
		services=new ArrayList<>();
	}
}
