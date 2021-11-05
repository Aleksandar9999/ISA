package com.isa.FishingBooker.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.User;


@Repository
public class UsersDAOInMemoryImpl implements UsersDAO {
	private List<User> users = new ArrayList<User>();

	@Override
	public List<User> getAll() {
		return users;
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
		users.add(item);
	}

}
