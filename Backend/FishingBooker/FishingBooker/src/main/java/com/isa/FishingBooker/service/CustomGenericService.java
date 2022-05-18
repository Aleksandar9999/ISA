package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.FishingBooker.service.interfaces.Service;

public class CustomGenericService<T> implements Service<T> {

	@Autowired 
	protected JpaRepository<T, Integer> repository;
	
	@Override
	@Transactional
	public void addNew(T item) {
		repository.save(item);
	}

	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Integer id) {
		return (T) repository.findById(id).get();
	}

	@Override
	public void update(T item) {
		repository.save(item);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
