package com.isa.FishingBooker.dao;

import java.util.List;

public interface DAO<T> {
	void addNew(T item);
	List<T> getAll();
	T getById(String id);
	void update(T item);
	void delete(String id);
}
