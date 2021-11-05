package com.isa.FishingBooker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dao.UsersDAO;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDAO dao;

	@Override
	public List<User> getAll() {
		return dao.getAll();
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

	@Override
	public void addNew(User item) {
		item.setStatus(Status.PENDING);
		item.setId(UUID.randomUUID().toString());
		dao.addNew(item);
	}

}
