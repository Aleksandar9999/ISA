package com.isa.FishingBooker.mapper;

import java.util.List;

public interface CustomModelMapper<E,T> {
	public T convertToDto(E entity);
	public E convertToEntity(T dto);
	public E convertToEntity(T dto,Class<? extends E> retclass);
	public List<T> convertToDtos(List<E> entities);
	public List<E> convertToEntities(List<T> dtos);
}
