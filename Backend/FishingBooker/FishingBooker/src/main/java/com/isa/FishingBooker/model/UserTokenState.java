package com.isa.FishingBooker.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserTokenState {
	private String jwt;
	private List<Role> roles;
	private int id;
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
	
	
	public int getId() {
		return id;
	}

	public UserTokenState setId(int id) {
		this.id = id;
		return this;
	}

	public String getRoles(){
		StringBuilder builder=new StringBuilder("");
		roles.stream().forEach(role->builder.append(role.getName()+" "));
		return builder.toString();
	}

}
