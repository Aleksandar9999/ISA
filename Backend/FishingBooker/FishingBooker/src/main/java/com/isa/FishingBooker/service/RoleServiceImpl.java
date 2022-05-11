package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.repository.RoleRepository;
import com.isa.FishingBooker.service.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;
	@Override
	public Long findRoleIdByName(String name) {
		return repository.findByName(name).getId();
	}

}
