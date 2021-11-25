package com.isa.FishingBooker.service;

import java.util.List;

public interface Service<T> {
	void addNew(T item);
	List<T> getAll();
	T getById(String id);
	void update(T item);
	void delete(String id);
}
