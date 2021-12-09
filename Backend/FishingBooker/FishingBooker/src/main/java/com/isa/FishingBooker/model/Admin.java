package com.isa.FishingBooker.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	public Admin() {
		super();
	}
	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.ADMIN_ROLE);
	}
}
