package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dao.UsersDAO;
import com.isa.FishingBooker.model.User;


@Service
public class ClientRegistrationService implements UsersService {
	
	@Autowired
	UsersDAO dao;

	public boolean Register(User user) {
		return true;
	}
	
	@Override
	public void addNew(User item) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
