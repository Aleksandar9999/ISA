package com.isa.FishingBooker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserTokenState {
	private String jwt;
	private List<Role> roles;
	public UserTokenState() {
	}

	public UserTokenState(String jwt, List<Role> roles) {
		super();
		this.jwt = jwt;
		this.roles = roles;
	}

	public String getJwt() {
		return jwt;
	}
	
	public String getRoles(){
		StringBuilder builder=new StringBuilder("");
		roles.stream().forEach(role->builder.append(role.getName()+" "));
		return builder.toString();
	}

}
