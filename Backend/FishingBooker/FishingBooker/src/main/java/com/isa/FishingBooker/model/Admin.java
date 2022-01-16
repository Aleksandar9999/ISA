package com.isa.FishingBooker.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	
	private boolean passwordChanged;
	
	public Admin() {
		super();
	}
	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.ADMIN_ROLE);
	}
	public boolean isPasswordChanged() {
		return passwordChanged;
	}
	public void setPasswordChanged(boolean changed) {this.passwordChanged=changed;}
	
	public void resetPassword(String newPassword) {
		this.setPassword(newPassword);
		this.passwordChanged=true;
	}
	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(Status.ADMIN_CONFIRMED);
	}
}
