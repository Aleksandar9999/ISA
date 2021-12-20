package com.isa.FishingBooker.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CustomModelMapperAbstract<E, T> implements CustomModelMapper<E, T> {

	@Autowired
	protected ModelMapper modelMapper;
	
	@Override
	public E convertToEntity(T dto, Class<E> retClass) {
		return this.modelMapper.map(retClass, retClass);
	}
	
	@Override
	public List<T> convertToDtos(List<E> entities) {
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<E> convertToEntities(List<T> dtos) {
		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
