package com.isa.FishingBooker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CustomServiceAbstract<T> implements Service<T> {

	@Autowired 
	protected JpaRepository<T, Integer> repository;
	
	@Override
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
		throw new UnsupportedOperationException();
	}

}
