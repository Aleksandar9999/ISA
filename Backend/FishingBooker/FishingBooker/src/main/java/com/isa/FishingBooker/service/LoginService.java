package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dao.UsersDAO;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;

@Service
public class LoginService{
	@Autowired
	private UsersDAO dao;
	
	public String Login(User user) {
		
		for(User u : dao.getAll()) {
									
			if(u.getPassword().equals(user.getPassword())) {
				if(u.getEmail().equals(user.getEmail())) {
					if(u.getStatus()!=Status.CONFIRMED) {
						return "You must confirm you status first.";
					}
					return "Successfuly loged in!";
				}
				return "Bad password.";
			}
		}
		
		return "Bad email or user do not exist.";
	}

}
