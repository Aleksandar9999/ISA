package com.isa.FishingBooker.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CustomModelMapperAbstract<E, T> implements CustomModelMapper<E, T> {

	@Override
	public E convertToEntity(T dto, Class<E> retClass) {
		return this.modelMapper.map(retClass, retClass);
	}

	@Autowired
	protected ModelMapper modelMapper;
	
	@Override
	public abstract T convertToDto(E entity);

	@Override
	public abstract E convertToEntity(T dto);

	@Override
	public List<T> convertToDtos(List<E> entities) {
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<E> convertToEntities(List<T> dtos) {
		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
